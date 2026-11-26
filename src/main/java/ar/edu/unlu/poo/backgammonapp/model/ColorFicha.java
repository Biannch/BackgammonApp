package ar.edu.unlu.poo.backgammonapp.model;

public enum ColorFicha {
    BLANCA,
    NEGRA;

    public ColorFicha opuesto() {
        return (this == BLANCA) ? NEGRA : BLANCA;
    }
}
