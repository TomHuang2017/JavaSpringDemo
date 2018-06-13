package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoHelloController {

    @RequestMapping("/hello")
    public String Say(){
        return "Hey, this is my code!";
    }
}
