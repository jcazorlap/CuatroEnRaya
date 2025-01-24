package org.iesalandalus.programacion.cuatroenraya.modelo;

import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class CuatroEnRaya {

    private static final int NUMERO_JUGADORES = 2;
    private Jugador[] jugadores;
    private Tablero tablero;

    public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
        if (jugador1 == null || jugador2 == null) {
            throw new IllegalArgumentException("Los jugadores no pueden ser nulos");
        }
        if (jugador1.colorFichas() == jugador2.colorFichas()) {
            throw new IllegalArgumentException("Los jugadores no pueden tener el mismo color de fichas");
        }

        jugadores = new Jugador[NUMERO_JUGADORES];
        jugadores[0] = jugador1;
        jugadores[1] = jugador2;
        tablero = new Tablero();
    }

    public boolean tirar(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser nulo");
        }

        boolean jugadaGanadora = false;
        boolean jugadaValida = false;

        do {
            try {
                int columna = Consola.leerColumna(jugador);
                jugadaGanadora = tablero.introducirFicha(columna, jugador.colorFichas());
                jugadaValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Por favor, intenta nuevamente.");
            }
        } while (!jugadaValida);

        return jugadaGanadora;
    }

    public void jugar() {
        boolean hayGanador = false;
        int turno = 0;

        System.out.println("\n--- Comienza el juego de Cuatro en Raya ---\n");

        while (!tablero.estaLleno() && !hayGanador) {
            Jugador jugadorActual = jugadores[turno % NUMERO_JUGADORES];
            System.out.printf("Turno de %s (%s):\n", jugadorActual.nombre(), jugadorActual.colorFichas());

            hayGanador = tirar(jugadorActual);

            if (!hayGanador) {
                turno++;
            }
        }

        if (hayGanador) {
            Jugador ganador = jugadores[turno % NUMERO_JUGADORES];
            System.out.printf("\n\uD83C\uDF89 ¡Felicidades, %s! Has ganado el juego. \uD83C\uDF89\n", ganador.nombre());
        } else {
            System.out.println("\n⚠ No hay más casillas libres. El juego ha terminado en empate. \u26A0\n");
        }
    }
}
