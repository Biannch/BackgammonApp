package ar.edu.unlu.poo.backgammonapp.model;

import java.util.ArrayList;
import java.util.List;

public class Reglamento {

    public List<Movimiento> obtenerMovimientosPosibles(Tablero tablero, Turno turno){
        List<Movimiento> listMov = new ArrayList<>();
        // recorre las pos donde el jugActual tiene fichas
        // verifica primero la barra. luego la salida
        // prueba cada valor de turno.getValoresDisponibles()
        // devuelve aquellos movs legales
        return listMov;
    }

    public boolean tieneMovimientosPosibles(Tablero tablero, Turno turno) {
        return false;
    }
}
