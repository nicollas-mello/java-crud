package com.voltando.project.controllers;

import com.voltando.project.dtos.UserCreateDto;
import com.voltando.project.dtos.UserResponseDto;
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
    public List<UserResponseDto> listAll() {
        return userEntityService.listUsers();
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserCreateDto userCreateDto) {
        UserEntity savedUser = userEntityService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserCreateDto userCreateDto,@PathVariable Integer id) {
        UserEntity updateUser = userEntityService.updateUser(userCreateDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userEntityService.deleteUser(id);
    }

}
