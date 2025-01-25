package com.Elaki.SpringSecurity.controller;

import com.Elaki.SpringSecurity.jwt.JwtUtil;
import com.Elaki.SpringSecurity.model.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class WelcomeController {
    //    not request auth

    @PostMapping("/public/needToken")
    public String needToken(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo.getUsername());
        System.out.println(userInfo.getRoles());
//        System.out.println(roles.toString());
       String token = JwtUtil.generateToken(userInfo.getUsername(), userInfo.getRoles());
        return "Hello, " + token;

    }

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "Hello, Public!";
    }

    @GetMapping("/public/hello123")
    public String publicEndpoint123() {
        return "Hello, Public123!";
    }

    @GetMapping("/private/hello")
    public String privateEndpoint() {
        System.out.println("privateEndpoint called");
            return "Hello, Private!";
    }

    @GetMapping("/private/userHello")
//    @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userEndpoint() {
        return "Hello, User!";
    }

    @GetMapping("/admin/adminHello")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminEndpoint() {
        return "Hello, Admin!";
    }


//    roll base auth



}
