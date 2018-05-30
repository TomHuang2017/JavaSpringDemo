package com.springinaction.springdio.autowried;

public class Saxophone implements Instrument {
    public Saxophone(){}

    @Override
    public void play() {
        System.out.println("Sax.......");
    }
}
