package ar.edu.unlu.poo.backgammonapp.model;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private static final int CANT_PUNTAS = 24;
    private final List<Punta> puntaList = new ArrayList<>();
    private Barra barraBlancas = new Barra();
    private Barra barraNegras = new Barra();
    private Salida salidaBlancas = new Salida();
    private Salida salidaNegras = new Salida();

    public Tablero(){
        for (int i = 0; i < CANT_PUNTAS; i++) {
            puntaList.add(new Punta(i+1));
        }
        inicializar();
    }

    public void inicializar() {
        // Fichas negras
        agregarFichasAPunta(24, ColorFicha.NEGRA, 2);
        agregarFichasAPunta(13, ColorFicha.NEGRA, 5);
        agregarFichasAPunta(8,  ColorFicha.NEGRA, 3);
        agregarFichasAPunta(6,  ColorFicha.NEGRA, 5);
        // fichas blancas
        agregarFichasAPunta(1,  ColorFicha.BLANCA, 2);
        agregarFichasAPunta(12, ColorFicha.BLANCA, 5);
        agregarFichasAPunta(17, ColorFicha.BLANCA, 3);
        agregarFichasAPunta(19, ColorFicha.BLANCA, 5);
    }

    private void agregarFichasAPunta(int indiceLista, ColorFicha color, int cantidad) {
        Punta punta = puntaList.get(indiceLista-1);
        for (int i = 0; i < cantidad; i++) {
            punta.agregarFicha(new Ficha(color));
        }
    }

    public Punta getPunta(int i) {
        return puntaList.get(i);
    }

    public int getCantidadEnBarra(ColorFicha colorFicha) {
        if(colorFicha == ColorFicha.NEGRA){
            return barraNegras.getCantidadFichas();
        }
        return barraBlancas.getCantidadFichas();
    }

    public int getCantidadEnSalida(ColorFicha colorFicha) {
        if(colorFicha == ColorFicha.NEGRA){
            return salidaNegras.getCantidadFichas();
        }
        return salidaBlancas.getCantidadFichas();
    }
}