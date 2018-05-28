package com.springinaction.springdio.xml;

public class Instrumentalist implements Performer {
    public Instrumentalist(){}

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    private String song;

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    private Instrument instrument;
    @Override
    public void perform() {
        System.out.println("Playing "+song+" :");
        instrument.play();
    }
}