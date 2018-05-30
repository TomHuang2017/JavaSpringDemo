package com.springinaction.springdio.autowried;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Instrumentalist implements Performer {
    public Instrumentalist(){}

    private String song;
    public String getSong() {
        return song;
    }
    public void setSong(String song) {
        this.song = song;
    }
    @Autowired
    @Qualifier("saxphone1")
    private Instrument instrument;

    @Autowired(required = false)
    @Qualifier("saxphone1")
    private Performer addtionalMessage;

    @Autowired
    @Qualifier("saxphone1")
    private void printAdditionalMessage(Instrument instrument)
    {
        System.out.println("This is an additional message");
    }
    @Override
    public void perform() {
        System.out.println("Playing "+song+" :");
        instrument.play();
        //addtionalMessage.play();
    }
}