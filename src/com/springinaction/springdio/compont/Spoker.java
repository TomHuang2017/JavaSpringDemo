package com.springinaction.springdio.compont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;

@Named("my_speaker")
public class Spoker implements Say {
    //@Autowired
    //@Qualifier("hello")
    @Inject
    @hello
    private Speak speak;

    public Spoker()
    {}
    @Override
    public void SayWord()
    {
        speak.Say();
    }

}
