package com.springinaction.springdio.xml;

public class Saxophone implements Instrument {
    public Saxophone(){}

    @Override
    public void play() {
        System.out.println("Sax.......");
    }
}
