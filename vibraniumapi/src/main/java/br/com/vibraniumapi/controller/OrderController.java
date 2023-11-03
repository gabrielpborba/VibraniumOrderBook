package br.com.vibraniumapi.controller;

import br.com.vibraniumapi.dto.OrderDto;
import br.com.vibraniumapi.repository.OrderRepository;
import br.com.vibraniumapi.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody OrderDto orderDto) throws JsonProcessingException {
        orderService.create(orderDto.toOrderEntity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}