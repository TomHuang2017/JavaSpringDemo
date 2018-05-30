package com.springinaction.springdio.autowried;

public class Sonnect29 implements Poem {
    private static String[] LINES=
            {"this is me","i am you"};
    public Sonnect29(){}
    @Override
    public void recite() {
        for (String line:LINES
             ) {
            System.out.println(line);
        }
    }
}
