package ar.edu.unlu.poo.backgammonapp.model;

public class Barra extends ContenedorFicha{

    public Barra(){
    }

    @Override
    public boolean puedeRecibirFicha(Ficha ficha) {
        return false;
    }
}
