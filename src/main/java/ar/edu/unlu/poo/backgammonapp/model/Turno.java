package ar.edu.unlu.poo.backgammonapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Turno {
    private boolean dadosTirados = false;
    private Jugador jugadorActual;
    private Jugador jugadorSiguiente;
    private final List<Integer> valoresDisponibles = new ArrayList<>();

    public Turno(Jugador jugadorActual, Jugador jugadorSiguiente){
        this.jugadorActual = jugadorActual;
        this.jugadorSiguiente = jugadorSiguiente;
    }

    public void primerTiro(Dado dado1, Dado dado2){
        int v1 = dado1.tirar();
        int v2 = dado2.tirar();

        if(v2 > v1){ // todo contemplar empate
            cambiarTurno();
        }
        this.valoresDisponibles.clear();
        Collections.addAll(this.valoresDisponibles, v1, v2);
        this.dadosTirados = true;
    }

    public void cambiarTurno(){
        Jugador temp = this.jugadorActual;
        this.jugadorActual = this.jugadorSiguiente;
        this.jugadorSiguiente = temp;
        this.dadosTirados = false;
        this.valoresDisponibles.clear();
    }

    public void tirarDados(Dado dado1, Dado dado2){
        if (yaTiroDados()){
            return;
        }
        int v1 = dado1.tirar();
        int v2 = dado2.tirar();

        this.valoresDisponibles.clear();
        Collections.addAll(this.valoresDisponibles, v1, v2);
        if (v1 == v2){
            Collections.addAll(this.valoresDisponibles, v1, v2);
        }
        this.dadosTirados = true;
    }

    public void consumirValor(int valorDado){
        this.valoresDisponibles.remove(Integer.valueOf(valorDado));
    }

    public boolean tieneValoresDisponibles(){
        return !this.valoresDisponibles.isEmpty();
    }

    public boolean yaTiroDados(){
        return this.dadosTirados;
    }

    public Jugador getJugadorActual(){
        return this.jugadorActual;
    }

    public Jugador getJugadorSiguiente(){
        return this.jugadorSiguiente;
    }

    public List<Integer> getValoresDisponibles(){
        return Collections.unmodifiableList(this.valoresDisponibles);
    }
}
