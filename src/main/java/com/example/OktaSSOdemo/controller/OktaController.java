package com.example.OktaSSOdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/okta")
@CrossOrigin("*")
public class OktaController {
/**This method is for example and testing on the first touch with Okta*/
    @GetMapping("/protected")
    public ResponseEntity<?> getPrincipal(@AuthenticationPrincipal Jwt user){
        return ResponseEntity.ok(user.getClaims());
    }
}
