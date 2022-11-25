package com.github.algmironov.glucose_data_measurement_service.controller;

import com.github.algmironov.glucose_data_measurement_service.model.User;
import com.github.algmironov.glucose_data_measurement_service.service.ResponseDealer;
import com.github.algmironov.glucose_data_measurement_service.service.UserService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestParam Long chatId, @RequestParam String username) {
        User user = new User();
        user.setUsername(username);
        user.setId(chatId);
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUser(id));
    }
}
