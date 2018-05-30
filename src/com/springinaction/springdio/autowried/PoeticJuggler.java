package com.springinaction.springdio.autowried;

public class PoeticJuggler extends Juggler {
    private Poem poem;
    public PoeticJuggler(Poem poem){
        this.poem=poem;
    }
    public PoeticJuggler(int beanbags,Poem poem){
        super(beanbags);
        this.poem=poem;
    }

    @Override
    public void perform() {
        super.perform();
        System.out.println("While reciting...");
        this.poem.recite();;
    }
}
