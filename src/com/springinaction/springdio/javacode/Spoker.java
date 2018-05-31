package com.springinaction.springdio.javacode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class Spoker implements Say {
    public Speak getSpeak() {
        return speak;
    }

    public void setSpeak(Speak speak) {
        this.speak = speak;
    }

    private Speak speak;

    public Spoker()
    {}
    @Override
    public void SayWord()
    {
        speak.Say();
    }

}
