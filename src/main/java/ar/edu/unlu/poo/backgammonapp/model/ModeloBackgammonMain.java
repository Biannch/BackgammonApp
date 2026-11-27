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
    private Jugador jugador1 = null;
    private Jugador jugador2 = null;
    private Turno turno = null;
    private Reglamento reglamento; // todo
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

    public boolean registrarJugador(String nombre){ // todo hacer boolean
        if(this.jugador1 == null){
            this.jugador1 = new Jugador(nombre);
        }
        else if(this.jugador2 == null){
            this.jugador2 = new Jugador(nombre);
            this.estadoPartida = EstadoPartida.PRIMER_TIRO;
        }
        else{
            return false;
        }
        return true;
    }

    public void sortearTurnoInicial(){
        this.turno = new Turno(jugador1, jugador2);
        turno.primerTiro(dado1, dado2);
    }

    public void repartirColores(ColorFicha color){
        getJugadorActual().setColorJugador(color);
        turno.getJugadorSiguiente().setColorJugador(color.opuesto());
        tablero.inicializar();
        this.estadoPartida = EstadoPartida.EN_CURSO;
        notificarObservadores();
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

    public boolean moverFicha(int origen, int destino){
        // le pide a reglamento los movimientosPosibles
        // busca si en la lista coincide alguno con origen, destino
        // si la lista está vacía ---> no hay movs. posibles, pasar turno
        // si nada coincide ---> mov. ilegal
        // si hay match, le pasamos el mov a tablero para que lo ejecute
        // ModeloBackgammonMain consume el dado del turno
        // se vuelven a consultar movs posibles (por si hay q pasar el turno otra vez)
        notificarObservadores();
        return false;
    }

    public boolean selectPuntaOrigen(int indice){
        // reglamento verifica si el color de la ficha coincide con el jugador
        // reglamento verifica si hay un número válido para esa ficha
        return false;
    }

    public boolean selectPuntaDestino(int indice){
        return false;
    }

    public void cambiarTurno(){
        turno.cambiarTurno();
        notificarObservadores();
    }

    public Tablero getTablero(){
        return tablero;
    }

    public Jugador getJugadorActual(){
        return turno.getJugadorActual();
    }

    public List<Integer> getDadosDisponibles(){
        return turno.getValoresDisponibles();
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
