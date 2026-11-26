package ar.edu.unlu.poo.backgammonapp.model;

import ar.edu.unlu.poo.backgammonapp.observer.Observable;
import ar.edu.unlu.poo.backgammonapp.observer.Observador;

import java.util.ArrayList;
import java.util.List;

public class ModeloBackgammonMain implements Observable {
    private List<Observador> observadores;

    private Tablero tablero;
    private Jugador jugadorBlancas;
    private Jugador jugadorNegras;
    private Turno turno;
    private Dado dado1;
    private Dado dado2;
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
        if(turno.estaDadosTirados()){
            return;
        }
        // el Modelo le pide al Turno que tire usando los dados del juego
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
        return turno.getJugadorActivo();
    }

    public boolean moverFicha(int origen, int destino){
        notificarObservadores();
        return false;
    }

    public void cambiarTurno(){
        if (turno.getJugadorActivo().getColorJugador() == ColorFicha.BLANCA) {
            turno.setJugadorActivo(jugadorNegras);
        } else {
            turno.setJugadorActivo(jugadorBlancas);
        }
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
