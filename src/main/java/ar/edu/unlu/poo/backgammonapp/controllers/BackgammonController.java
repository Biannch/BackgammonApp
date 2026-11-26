package ar.edu.unlu.poo.backgammonapp.controllers;

import ar.edu.unlu.poo.backgammonapp.model.ColorFicha;
import ar.edu.unlu.poo.backgammonapp.model.EstadoPartida;
import ar.edu.unlu.poo.backgammonapp.model.ModeloBackgammonMain;
import ar.edu.unlu.poo.backgammonapp.observer.Observador;
import ar.edu.unlu.poo.backgammonapp.views.VistaConsola;

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
        prepararPartida();
        int opcion = -1;
        while (opcion != 0){
            opcion = vistaConsol.obtenerOpcion();

            switch (opcion){
                case 1 -> { // tirar dados
                    if(!modelo.tirarDados()){
                        vistaConsol.mostrarMensaje(">> Usted ya tiene dados para usar.");
                    }
                    vistaConsol.mostrarDados(modelo.getDadosDisponibles());
                }
                case 2 -> { // mover ficha
                    int origen = vistaConsol.pedirPunta(">> Punta origen (1-24): ");
                    int destino = vistaConsol.pedirPunta(">> Punta destino (1-24): ");
                    if (!modelo.moverFicha(origen, destino)){
                        vistaConsol.mostrarMensaje("Movimiento inválido según el reglamento.");
                    }
                }
                case 3 -> modelo.cambiarTurno(); // pasar turno
                case 0 -> vistaConsol.mostrarMensaje("Saliendo del juego...");
                default -> vistaConsol.mostrarMensaje("Opción incorrecta.");
            }
        }
    }

    private void prepararPartida(){
        if(modelo.estadoPartida() == EstadoPartida.NO_INICIADA){ // registrar usuarios
            String str1 = vistaConsol.pedirString(">> Jugador 1, ingrese su nombre: ");
            modelo.registrarJugador(str1); // todo que perdir string verifique no ingresar nada nulo
            String str2 = vistaConsol.pedirString(">> Jugador 2, ingrese su nombre: ");
            modelo.registrarJugador(str2);
            vistaConsol.mostrarMensaje("\n>> Bienvenid@s " + str1 + " y " + str2);
        }
        if(modelo.estadoPartida() == EstadoPartida.PRIMER_TIRO){
            modelo.sortearTurnoInicial();
            vistaConsol.mostrarMensaje("Se han tirado los dados para ver quien comienza.");
            ColorFicha colorPrimerJug = vistaConsol.pedirColor(modelo.getJugadorActual().getNombre());
            modelo.repartirColores(colorPrimerJug);
        }
    }

    @Override
    public void actualizar() {
        // Cuando el modelo avisa que hubo un cambio, actualizamos la vista
        vistaConsol.mostrarTablero(modelo.getTablero());
        vistaConsol.mostrarMensaje(">> TURNO ACTUAL -> " + modelo.getJugadorActual().getNombre());
        vistaConsol.mostrarDados(modelo.getDadosDisponibles());
    }

//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}
