package com.example.EjercicioSesion789SpringBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hola")
    public String hello() {
        return "Hola mundo, ¿Cómo estamos?";
    }

}
