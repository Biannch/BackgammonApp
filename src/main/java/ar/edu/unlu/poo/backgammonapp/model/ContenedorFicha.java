package ar.edu.unlu.poo.backgammonapp.model;

import java.util.ArrayDeque;
import java.util.ArrayList;

public abstract class ContenedorFicha{
    private ArrayDeque<Ficha> fichaStack; // Atributo compartido (estado)

    public ContenedorFicha(){
        this.fichaStack = new ArrayDeque<>();
    }

    public void agregarFicha(Ficha ficha) {
        if (puedeRecibirFicha(ficha)) {
            fichaStack.push(ficha);
        }
    }

    public Ficha sacarFicha() {
        if (estaVacia()){
            return null;
        }
        return fichaStack.pop();
    }

    public boolean estaVacia(){
        return fichaStack.isEmpty();
    }

    public int getCantidadFichas(){ // Metodo ya implementado que funciona igual para todos
        return this.fichaStack.size();
    }

    // Metodo abstracto: cada subclase decide sus propias reglas para aceptar una ficha
    public abstract boolean puedeRecibirFicha(Ficha ficha);
}

