package com.codeanywhere.springboot_template;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Hello World from Spring Boot on Codeanywhere!";
    }
}