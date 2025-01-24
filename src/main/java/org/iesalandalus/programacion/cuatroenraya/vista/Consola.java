package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

    private Consola(){}

    public String leerNombre() {
        String nombre = null;
        do {
            System.out.print("Introduce el nombre del jugador:");
            nombre = Entrada.cadena();
        } while (nombre == null);
        return nombre;
    }

    public Ficha elegirColorFichas() {
        int color = 3;
        do {
            System.out.print("Introduce el color de fichas(0 = azul/1= verde):" );
            color = Entrada.entero();
        } while(color != 0 || color != 1);
        Ficha ficha = Ficha.values()[color];
        return ficha;
    }

    public Jugador leerJugador() {
        String nombre = leerNombre();
        Ficha colorFicha = elegirColorFichas();
        return new Jugador(nombre, colorFicha);
    }

    public Jugador leerJugador(Ficha ficha) {
        String nombre = leerNombre();
        return new Jugador(nombre, ficha);
    }

    public static int leerColumna(Jugador jugador) {
        int columna;
        do {
            System.out.printf("%s, elige la columna (1-7) donde quieres introducir tu ficha: ", jugador.nombre());
            columna = Entrada.entero();
            if (columna < 1 || columna > 7) {
                System.out.println("Columna inválida. Debe estar entre 1 y 7. Inténtalo de nuevo.");
            }
        } while (columna < 1 || columna > 7);
        return columna - 1;
    }
}
