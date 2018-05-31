package com.springinaction.springdio.compont;

import org.springframework.stereotype.Component;

import javax.inject.Named;

@Named("nihao")
//@Component("nihao")
@nihao
public class SpeakNiHao implements Speak {
    @Override
    public void Say() {
        System.out.println("Say NiHao");
    }
}
