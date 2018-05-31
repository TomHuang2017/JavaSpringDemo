package com.springinaction.springdio.javacode;

import org.springframework.stereotype.Component;

public class SpeakHello implements Speak {
    public SpeakHello(){}
    private  String name="default Speak Hello name";
    public SpeakHello(String name){
        this.name=name;
    }
    @Override
    public void Say() {
        System.out.println("Speak Hello:"+this.name);
    }
}
