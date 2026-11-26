package ar.edu.unlu.poo.backgammonapp.model;

public class Jugador {
    private final String nombre;
    private ColorFicha colorJugador = null;

    public Jugador(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public ColorFicha getColorJugador(){
        return colorJugador;
    }

    public void setColorJugador(ColorFicha color){
        this.colorJugador = color;
    }
}
