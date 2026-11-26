package ar.edu.unlu.poo.backgammonapp;

import ar.edu.unlu.poo.backgammonapp.controllers.BackgammonController;
import ar.edu.unlu.poo.backgammonapp.model.ModeloBackgammonMain;
import ar.edu.unlu.poo.backgammonapp.views.VistaConsola;

public class AppConsola {

    public static void main(String[] args){
        ModeloBackgammonMain modelo = new ModeloBackgammonMain();
        VistaConsola vista = new VistaConsola();
        BackgammonController controlador = new BackgammonController(modelo, vista);

        controlador.iniciar();
    }
}
