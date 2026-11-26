package ar.edu.unlu.poo.backgammonapp.model;

import ar.edu.unlu.poo.backgammonapp.observer.Observable;
import ar.edu.unlu.poo.backgammonapp.observer.Observador;

import java.util.ArrayList;
import java.util.List;

public class ModeloBackgammonMain implements Observable {
    private List<Observador> observadores;

    private final Tablero tablero;
    private final Dado dado1;
    private final Dado dado2;
    private Jugador jugadorBlancas;
    private Jugador jugadorNegras;
    private Turno turno;
    private Reglamento reglamento;
    private boolean partidaIniciada = false;

    public ModeloBackgammonMain(){
        this.observadores = new ArrayList<>();
        this.tablero = new Tablero();
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        this.reglamento = new Reglamento();
        // turno y jug se definen luego de tirar los dados y que salga el mas grande
    }

    public void tirarDados(){
        if(turno.yaTiroDados()){
            return;
        }
        // el Modelo le pide al Turno que el jugador actual tire los dados del juego
        turno.tirarDados(dado1, dado2);

        // Si con los dados que salieron no tiene ningún movimiento legal, pierde el turno
        if (!reglamento.tieneMovimientosPosibles(tablero, turno)){
            cambiarTurno();
        }
        notificarObservadores();
    }

    public Tablero getTablero(){
        return tablero;
    }

    public Jugador getJugadorActual(){
        return turno.getJugadorActual();
    }

    public boolean moverFicha(int origen, int destino){
        notificarObservadores();
        return false;
    }

    public void cambiarTurno(){
        turno.cambiarTurno();
        notificarObservadores();
    }

    public Object getDadosDisponibles(){
        return null;
    }

    public boolean esPartidaIniciada(){
        return partidaIniciada;
    }

    @Override
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (Observador observador : observadores){
            observador.actualizar();
        }
    }
}
