package ar.edu.unlu.poo.backgammonapp.model;

public class Ficha {
    private final ColorFicha color;
    // private int posicion;

    public Ficha(ColorFicha color){
        this.color = color;
    }

    public ColorFicha getColor(){
        return this.color;
    }

    public boolean esDelColor(ColorFicha color) {
        return this.color == color;
    }
}
