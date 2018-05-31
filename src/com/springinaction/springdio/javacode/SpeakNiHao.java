package com.springinaction.springdio.javacode;

import org.springframework.stereotype.Component;

public class SpeakNiHao implements Speak {
    @Override
    public void Say() {
        System.out.println("Say NiHao");
    }
}
