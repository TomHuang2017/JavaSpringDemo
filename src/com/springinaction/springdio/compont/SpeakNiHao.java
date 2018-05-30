package com.springinaction.springdio.compont;

import org.springframework.stereotype.Component;

@Component("nihao")
public class SpeakNiHao implements Speak {
    @Override
    public void Say() {
        System.out.println("Say NiHao");
    }
}
