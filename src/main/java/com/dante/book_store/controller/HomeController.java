package com.dante.book_store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/user/home")
    public String userPage() {
        return "Hello World! with user";
    }

    @GetMapping("admin/home")
    public String adminPage() {
        return "Hello World! with admin";
    }
}
