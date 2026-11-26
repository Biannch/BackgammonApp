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
    private Jugador jugador1;
    private Jugador jugador2;
    private Turno turno;
    private Reglamento reglamento;
    private EstadoPartida estadoPartida;

    public ModeloBackgammonMain(){
        this.observadores = new ArrayList<>();
        this.tablero = new Tablero();
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        this.reglamento = new Reglamento();
        this.estadoPartida = EstadoPartida.NO_INICIADA;
        // turno se define luego de tirar los dados y que salga el mas grande
    }

    public void registrarUsuarios(String nombre1, String nombre2) {
        this.jugador1 = new Jugador(nombre1);
        this.jugador2 = new Jugador(nombre2);
        this.estadoPartida = EstadoPartida.PRIMER_TIRO;
    }

    public void comenzarPartida(){
        this.turno = new Turno(jugador1, jugador2);
        turno.primerTiro(dado1, dado2);
        // cada jugador tira un solo dado
        // el jugador que sacó mayor puntaje empieza
        // elige un color y usa esos 2 dados para su primer movimiento
        // al otro jugador se le asigna el color restante
    }

    public void repartirColores(ColorFicha color){
        getJugadorActual().setColorJugador(color);
        turno.getJugadorSiguiente().setColorJugador(color.opuesto());
    }

    public boolean tirarDados(){
        if(turno.yaTiroDados()){
            return false; // como ya tiene dados, no los tira
        }
        turno.tirarDados(dado1, dado2);
        // el Modelo le pide al Turno que el jugador actual tire los dados del juego

//        // Si con los dados que salieron no tiene ningún movimiento legal, pierde el turno
//        if (!reglamento.tieneMovimientosPosibles(tablero, turno)){
//            cambiarTurno();
//        }
        notificarObservadores();
        return true;
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

    public EstadoPartida estadoPartida(){
        return estadoPartida;
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
