package com.amreshpro.journal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping("/")
    String healthCheck() {
        return "OK";
    }
    @GetMapping("/user2")
    String helloUser2() {
        return "Hello User";
    }

    @GetMapping("/admin")
    String helloAdmin() {
        return "Hello Admin";
    }

}
