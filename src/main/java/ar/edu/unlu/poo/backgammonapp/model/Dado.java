package ar.edu.unlu.poo.backgammonapp.model;

import java.util.Random;

public class Dado {
    private int cara;
    private static final Random numeroRandom = new Random();

    public Dado(){
    }

    public int tirar(){
        this.cara = numeroRandom.nextInt(6) + 1;
        return cara;
    }

    public int getCara(){
        return this.cara;
    }
}
