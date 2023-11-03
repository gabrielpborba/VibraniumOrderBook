package br.com.orderbookmanager.service.impl;

import br.com.orderbookmanager.component.TradeQueueProducer;
import br.com.orderbookmanager.model.OrderEntity;
import br.com.orderbookmanager.model.OrderType;
import br.com.orderbookmanager.model.TradeEntity;
import br.com.orderbookmanager.repository.OrderRepository;
import br.com.orderbookmanager.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TradeQueueProducer tradeQueueProducer;

    public void matchOrders(OrderEntity order) throws JsonProcessingException {
        OrderType matchingOrderType = getMatchingOrderType(order.getType());
        List<OrderEntity> ordersToMatch = findMatchingOrders(order.getPrice(), matchingOrderType);

        List<OrderEntity> filteredOrders = ordersToMatch.stream()
                .filter(orderWithoutUser -> !orderWithoutUser.getUser().getId().equals(order.getUser().getId()))
                .toList();

        for (OrderEntity orderToMatch : filteredOrders) {
            if(order.getQuantity()>0){
                int matchedQuantity = Math.min(order.getQuantity(), orderToMatch.getQuantity());

                updateOrderQuantities(order, orderToMatch, matchedQuantity);
                updateOrderAvailability(order, orderToMatch);

                saveOrders(order, orderToMatch);
                sendTradeToQueue(order, orderToMatch, matchedQuantity);
            }
        }
    }

    private OrderType getMatchingOrderType(OrderType orderType) {
        return OrderType.SELL.equals(orderType) ? OrderType.BUY : OrderType.SELL;
    }

    private List<OrderEntity> findMatchingOrders(double price, OrderType orderType) {
        return orderRepository.findByPriceAndTypeAndAvailable(price, orderType, true);
    }

    private void updateOrderQuantities(OrderEntity order, OrderEntity orderToMatch, int matchedQuantity) {
        order.setQuantity(order.getQuantity() - matchedQuantity);
        orderToMatch.setQuantity(orderToMatch.getQuantity() - matchedQuantity);
    }

    private void updateOrderAvailability(OrderEntity order, OrderEntity orderToMatch) {
        if (order.getQuantity() == 0) {
            order.setAvailable(false);
        }
        if (orderToMatch.getQuantity() == 0) {
            orderToMatch.setAvailable(false);
        }
    }

    private void saveOrders(OrderEntity order, OrderEntity orderToMatch) {
        orderRepository.save(order);
        orderRepository.save(orderToMatch);
    }

    private void sendTradeToQueue(OrderEntity order, OrderEntity orderToMatch, int matchedQuantity) throws JsonProcessingException {
        TradeEntity trade = createTrade(order, orderToMatch, matchedQuantity);

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        tradeQueueProducer.sendMessage(objectMapper.writeValueAsString(trade));
    }

    private TradeEntity createTrade(OrderEntity order, OrderEntity orderToMatch, int matchedQuantity) {
        if (OrderType.BUY.equals(order.getType())) {
            return new TradeEntity(order.getUser().getId(), orderToMatch.getUser().getId(), order.getPrice(), matchedQuantity);
        } else {
            return new TradeEntity(orderToMatch.getUser().getId(), order.getUser().getId(), order.getPrice(), matchedQuantity);
        }
    }

    public void save(OrderEntity order){
        orderRepository.save(order);
    }


}
















