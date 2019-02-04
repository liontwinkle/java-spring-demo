package com.codesample.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestController {
    @GetMapping(path = "/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody String testAdmin() {
        return "Admin ok";
    }

    @GetMapping(path = "/user")
    @PreAuthorize("hasRole('USER')")
    public @ResponseBody String testUser() {
        return "User ok";
    }
}
