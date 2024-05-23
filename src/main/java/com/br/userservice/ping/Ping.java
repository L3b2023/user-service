package com.br.userservice.ping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ping {
    @GetMapping("/ping")
    public String check(){
        return "pong!";
    }
}