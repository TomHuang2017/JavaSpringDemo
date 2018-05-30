package com.springinaction.springdio.compont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("hello")
public class SpeakHello implements  Speak{
    @Override
    public void Say() {
        System.out.println("Speak Hello");
    }
}
