package ar.edu.unlu.poo.backgammonapp.views;

import ar.edu.unlu.poo.backgammonapp.model.ColorFicha;
import ar.edu.unlu.poo.backgammonapp.model.Punta;
import ar.edu.unlu.poo.backgammonapp.model.Tablero;

import java.util.List;
import java.util.Scanner;

public class VistaConsola {
    private final Scanner scan = new Scanner(System.in); // instancia scanner cuya entrada va a ser el teclado

    public void mostrarTablero(Tablero tablero){
        System.out.println("+================================ TABLERO DE BACKGAMMON =======================================+");
        System.out.println("|    13    14    15    16    17    18    | BAR |    19    20    21    22    23    24    | CAS ||");
        System.out.println("|==============================================================================================|");

        // Fila superior (picos 13 al 18 -> índices 12 al 17)
        System.out.print("|  ");
        for (int i = 12; i <= 17; i++){
            System.out.print("\\" + informacionPunta(tablero.getPunta(i)) + "/");
        }
        System.out.printf("  | N%02d |  ", tablero.getCantidadEnBarra(ColorFicha.NEGRA));
        // Fila superior (picos 19 al 24 -> índices 18 al 23)
        for (int i = 18; i <= 23; i++){
            System.out.print("\\" + informacionPunta(tablero.getPunta(i)) + "/");
        }
        System.out.printf("  | B%02d || %n", tablero.getCantidadEnSalida(ColorFicha.BLANCA));

        // Triángulos decorativoss
        System.out.println("|   \\  /  \\  /  \\  /  \\  /  \\  /  \\  /   |     |   \\  /  \\  /  \\  /  \\  /  \\  /  \\  /   |     ||");
        System.out.println("|    \\/    \\/    \\/    \\/    \\/    \\/    |     |    \\/    \\/    \\/    \\/    \\/    \\/    |     ||");
        System.out.println("|                                        |~+~+~|                                        |_____||");
        System.out.println("|                                        |~+~+~|                                        |     ||");
        System.out.println("|    /\\    /\\    /\\    /\\    /\\    /\\    |     |    /\\    /\\    /\\    /\\    /\\    /\\    |     ||");
        System.out.println("|   /  \\  /  \\  /  \\  /  \\  /  \\  /  \\   |     |   /  \\  /  \\  /  \\  /  \\  /  \\  /  \\   |     ||");

        // Fila inferior (picos 12 bajando a 1 -> índices 11 bajando a 0)
        System.out.print("|  ");
        for (int i = 11; i >= 6; i--) {
            System.out.print("/" + informacionPunta(tablero.getPunta(i)) + "\\");
        }
        System.out.printf("  | B%02d |  ", tablero.getCantidadEnBarra(ColorFicha.BLANCA));
        for (int i = 5; i >= 0; i--) {
            System.out.print("/" + informacionPunta(tablero.getPunta(i)) + "\\");
        }
        System.out.printf("  | N%02d || %n", tablero.getCantidadEnSalida(ColorFicha.NEGRA));

        System.out.println("|==============================================================================================|");
        System.out.println("|    12    11    10    09    08    07    | BAR |    06    05    04    03    02    01    | CAS ||");
        System.out.println("+==============================================================================================+");
        System.out.printf("| CASA -> Blancas: %d/15 | Negras: %d/15                                                        ||%n",
                tablero.getCantidadEnSalida(ColorFicha.BLANCA),
                tablero.getCantidadEnSalida(ColorFicha.NEGRA));
        System.out.println("+==============================================================================================+");
    }

    private String informacionPunta(Punta punta){
        if (punta.estaVacia()) return "[--]";
        String letra = (punta.getColorDom() == ColorFicha.BLANCA) ? "B" : "N";
        return String.format("[%d%s]", punta.getCantidadFichas(), letra);
    }

    public int obtenerOpcion(){
        int opcion;
        System.out.print("\n1. Tirar dados | 2. Mover ficha | 3. Pasar turno | 0. Salir\n");
        do {
            opcion = pedirInt("Opción: ");
        } while (opcion < 1 || opcion > 3);
        return opcion;
    }

    public int pedirPunta(String mensaje){ // todo borrar esto que es básicamente pedir int
        System.out.print(mensaje);
        int num = scan.nextInt();
        return (num >= 1 && num <= 24) ? (num - 1) : num; // Solo resta 1 para pasar de 1..24 a 0..23
    }

    public ColorFicha pedirColor(String nombreJugador){
        int opcion;
        do {
            opcion = pedirInt("%n>> " + nombreJugador + ", usted comienza. Elija un color:%n1. Blanco%n2. Negro%n>> ");
        } while (opcion != 1 && opcion != 2);
        return (opcion == 1) ? ColorFicha.BLANCA : ColorFicha.NEGRA;
    }

    public String pedirString(String mensaje){
        System.out.printf(mensaje);
        while(!scan.hasNext()){
            System.out.println("Error: eso no es un string.");
            scan.next();
            System.out.printf(mensaje);
        }
        String str = scan.next();
        scan.nextLine(); // limpiar el enter
        return str;
    }

    public int pedirInt(String mensaje){
        System.out.printf(mensaje);
        while(!scan.hasNextInt()){
            System.out.println("Error: eso no es un número entero.");
            scan.next();
            System.out.printf(mensaje);
        }
        int num = scan.nextInt();
        scan.nextLine(); // limpiar el enter
        return num;
    }

    public void mostrarDados(List<Integer> dados){
        System.out.println("Dados disponibles: " + dados);
    }

    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
}
