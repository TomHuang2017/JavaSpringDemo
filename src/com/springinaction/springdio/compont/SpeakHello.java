package com.springinaction.springdio.compont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Named("hello")
//@Component("hello")
@hello
public class SpeakHello implements Speak {

    public SpeakHello(){}
    private String name = "default Speak Hello name";

    public SpeakHello(String name) {
        this.name = name;
    }

    @Override
    public void Say() {
        System.out.println("Speak Hello:"+this.name);
    }
}
