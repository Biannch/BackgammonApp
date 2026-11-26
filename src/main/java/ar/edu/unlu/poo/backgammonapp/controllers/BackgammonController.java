package ar.edu.unlu.poo.backgammonapp.controllers;

import ar.edu.unlu.poo.backgammonapp.model.EstadoPartida;
import ar.edu.unlu.poo.backgammonapp.model.ModeloBackgammonMain;
import ar.edu.unlu.poo.backgammonapp.observer.Observador;
import ar.edu.unlu.poo.backgammonapp.views.VistaConsola;

import java.util.List;

public class BackgammonController implements Observador {
    // el controlador conoce algo del modelo, en este caso la clase main de entrada al modelo
    private ModeloBackgammonMain modelo;
    private VistaConsola vistaConsol;

    public BackgammonController(ModeloBackgammonMain modelo, VistaConsola vista){
        this.modelo = modelo;
        this.vistaConsol = vista;
        this.modelo.agregarObservador(this); // el controlador se auto-agrega como observador del modelo
    }

    public void iniciar(){

        if(modelo.estadoPartida() == EstadoPartida.NO_INICIADA){ // si no está iniciada la partida
            // llamar al registro de nombres de los usuarios
            // la vista pide los datos
            String str1 = vistaConsol.pedirString("Jugador 1: Ingrese su nombre: ");
            String str2 = vistaConsol.pedirString("Jugador 2: Ingrese su nombre: ");
            // el controlador le pasa los nombres al modelo
            modelo.registrarUsuarios(str1, str2);
            vistaConsol.mostrarMensaje("Bienvenidos " + str1 + " y " + str2);
        }
        else if(modelo.estadoPartida() == EstadoPartida.PRIMER_TIRO){
            // desempatar para ver quien empieza
            modelo.primerLanzamiento();
        }

        int opcion = -1;
        while (opcion != 0){
            vistaConsol.mostrarTablero(modelo.getTablero());
            //vistaConsol.mostrarMensaje("Turno actual: " + modelo.getJugadorActual().getNombre());

            //todo if con opcion de iniciar partida???
            opcion = vistaConsol.obtenerOpcion();

            switch (opcion){
                case 1 -> modelo.tirarDados();
                case 2 -> {
                    int origen = vistaConsol.pedirPunta("Punta origen (1-24): ");
                    int destino = vistaConsol.pedirPunta("Punta destino (1-24): ");
                    if (!modelo.moverFicha(origen, destino)){
                        vistaConsol.mostrarMensaje("Movimiento inválido según el reglamento.");
                    }
                }
                case 3 -> modelo.cambiarTurno();
                case 0 -> vistaConsol.mostrarMensaje("Saliendo del juego...");
                default -> vistaConsol.mostrarMensaje("Opción incorrecta.");
            }
        }
    }

    @Override
    public void actualizar() {
        // Cuando el modelo avisa que hubo un cambio, actualizamos la vista
        vistaConsol.mostrarTablero(modelo.getTablero());
        vistaConsol.mostrarDados((List<Integer>) modelo.getDadosDisponibles());
    }

//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}
