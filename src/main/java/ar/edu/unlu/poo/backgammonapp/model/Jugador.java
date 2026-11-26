package ar.edu.unlu.poo.backgammonapp.model;

import java.util.Objects;

public class Jugador {
    private final String nombre;
    private ColorFicha colorJugador;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public void setColorJugador(ColorFicha color){
        this.colorJugador = color;
    }

    public String getNombre(){
        return nombre;
    }

    public ColorFicha getColorJugador(){
        return colorJugador;
    }
}
