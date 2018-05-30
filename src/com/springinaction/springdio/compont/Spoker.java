package com.springinaction.springdio.compont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("my_speaker")
public class Spoker implements Say {
    @Autowired
    @Qualifier("hello")
    private Speak speak;

    public Spoker()
    {}
    @Override
    public void SayWord()
    {
        speak.Say();
    }

}
