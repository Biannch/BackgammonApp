package ar.edu.unlu.poo.backgammonapp.model;

public class Punta extends ContenedorFicha {
    private final int indice;
    private ColorFicha colorDom;

    public Punta(int indice){
        this.indice = indice;
    }

    public ColorFicha getColorDom(){
        return colorDom;
    }

    public int getIndice(){
        return indice;
    }

    @Override
    public Ficha sacarFicha() {
        Ficha f = super.sacarFicha();
        if (estaVacia()) {
            colorDom = null;
        }
        return f;
    }

    @Override
    public void agregarFicha(Ficha ficha) {
        if (puedeRecibirFicha(ficha)) {
            super.agregarFicha(ficha); // Usa el del padre sin tocar fichaStack
            colorDom = ficha.getColor();
        }
    }

    @Override
    public boolean puedeRecibirFicha(Ficha ficha){
        return estaVacia() || ficha.getColor() == colorDom;
    }
}
