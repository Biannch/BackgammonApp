package ar.edu.unlu.poo.backgammonapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Turno {
    private boolean dadosTirados = false;
    private Jugador jugadorActivo;
    private List<Integer> movimientosDisponibles = new ArrayList<>();

    public Turno(Jugador jugadorActivo) {
        this.jugadorActivo = jugadorActivo;
    }

    public void tirarDados(Dado dado1, Dado dado2) {
        int v1 = dado1.tirar();
        int v2 = dado2.tirar();

        this.movimientosDisponibles.clear();
        Collections.addAll(this.movimientosDisponibles, v1, v2);
//        this.movimientosDisponibles.add(v1);
//        this.movimientosDisponibles.add(v2);

        if (v1 == v2){
            Collections.addAll(this.movimientosDisponibles, v1, v2);
        }
        this.dadosTirados = true; //todo chequear si podría dar error
    }

    public boolean estaDadosTirados(){
        return this.dadosTirados;
    }

    public Jugador getJugadorActivo() {
        return jugadorActivo;
    }

    public List<Integer> getMovimientosDisponibles(){
        return movimientosDisponibles;
    }

    public void setJugadorActivo(Jugador jugador){
        this.jugadorActivo = jugador;
    }
}
