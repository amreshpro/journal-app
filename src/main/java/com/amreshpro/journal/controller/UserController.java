package com.amreshpro.journal.controller;

import com.amreshpro.journal.entity.UserEntity;
import com.amreshpro.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Boolean> saveUser(@RequestBody UserEntity userEntity) {
        boolean isSaved = userService.saveUser(userEntity);
        return new ResponseEntity<>(isSaved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUser();
        return users.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserEntity>> getUserById(@PathVariable ObjectId id) {
        Optional<UserEntity> userEntity = userService.getUserById(id);
        return userEntity.map(user -> new ResponseEntity<>(Optional.of(user), HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping
    public ResponseEntity<Boolean> updateUserById(@RequestBody UserEntity userEntity) {
        boolean isUpdated = userService.updateUserById(userEntity);
        return isUpdated ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable ObjectId id) {
        boolean isDeleted = userService.deleteUserById(id);
        return isDeleted ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
