package br.com.vibraniumapi.controller;

import br.com.vibraniumapi.dto.OrderDto;
import br.com.vibraniumapi.repository.OrderRepository;
import br.com.vibraniumapi.model.OrderEntity;
import br.com.vibraniumapi.model.OrderType;
import br.com.vibraniumapi.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;


    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody OrderDto orderDto) throws JsonProcessingException {
        orderService.create(orderDto.toOrderEntity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/order-book")
    public ModelAndView getOrderBook(Model model) {
        List<OrderEntity> buyOrders = orderRepository.findAllByTypeAndQuantityGreaterThanOrderByPriceDesc(OrderType.BUY, 0);
        List<OrderEntity> sellOrders = orderRepository.findAllByTypeAndQuantityGreaterThanOrderByPriceAsc(OrderType.SELL, 0);

        model.addAttribute("buyOrders", buyOrders);
        model.addAttribute("sellOrders", sellOrders);

        return new ModelAndView("orderbook", model.asMap());

    }
}