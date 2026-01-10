package com.voltando.project.controllers;

import com.voltando.project.entities.UserEntity;
import com.voltando.project.services.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntityController {

    private final UserEntityService userEntityService;

    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping
    public List<UserEntity> listAll() {
        return userEntityService.listAll();
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        UserEntity savedUser = userEntityService.createUser(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
