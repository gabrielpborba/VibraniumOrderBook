package br.com.vibraniumapi.controller;

import br.com.vibraniumapi.model.OrderEntity;
import br.com.vibraniumapi.model.OrderType;
import br.com.vibraniumapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RestController
@RequestMapping("/order")
@Validated
public class ViewController {
    @Autowired
    private OrderRepository orderRepository;
    @GetMapping("/order-book")
    public ModelAndView getOrderBook(Model model) {
        List<OrderEntity> buyOrders = orderRepository.findAllByTypeAndQuantityGreaterThanOrderByPriceDesc(OrderType.BUY, 0);
        List<OrderEntity> sellOrders = orderRepository.findAllByTypeAndQuantityGreaterThanOrderByPriceAsc(OrderType.SELL, 0);

        model.addAttribute("buyOrders", buyOrders);
        model.addAttribute("sellOrders", sellOrders);

        return new ModelAndView("orderbook", model.asMap());
    }
}
