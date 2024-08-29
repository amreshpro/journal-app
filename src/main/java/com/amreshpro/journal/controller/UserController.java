package com.amreshpro.journal.controller;

import com.amreshpro.journal.entity.UserEntity;
import com.amreshpro.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/User")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Boolean> saveUser(@RequestBody UserEntity UserEntity) {
        return new ResponseEntity<>(UserService.saveUser(UserEntity), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> userEntity = userService.getAllUser();
        if (!userEntity.isEmpty()) {
            return new ResponseEntity<>(userEntity, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserEntity>> getUserById(@PathVariable ObjectId id) {

        Optional<UserEntity> userEntity = userService.getUserById(id);
        if (!userEntity.isEmpty()) {
            return new ResponseEntity<>(userEntity, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

    }

    @PatchMapping
    public ResponseEntity<Boolean> updateUserById(@RequestBody UserEntity userEntity) {

        if (userService.updateUserById(userEntity)) {
            return new ResponseEntity<>(true, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);

        }


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable ObjectId id) {
        Boolean isSuccessfullyDeleted = UserService.deleteUserById(id);
        if(isSuccessfullyDeleted){
            return new ResponseEntity<>(true, HttpStatus.OK);

        }
        return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);

    }


}
