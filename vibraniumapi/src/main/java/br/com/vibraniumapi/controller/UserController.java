package br.com.vibraniumapi.controller;

import br.com.vibraniumapi.dto.UserDto;
import br.com.vibraniumapi.service.UserService;
import br.com.vibraniumapi.util.UserConverter;
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
