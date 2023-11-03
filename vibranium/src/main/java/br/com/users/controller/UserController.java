package br.com.users.controller;

import br.com.users.dto.OrderDto;
import br.com.users.dto.UserDto;
import br.com.users.model.UserEntity;
import br.com.users.service.UserService;
import br.com.users.util.UserConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return UserConverter.convertToDTOList(userService.findAll());

    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody UserDto userDto) {
        userService.create(userDto.toUserEntity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
