package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {

    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] tablero;

    public Tablero() {
        tablero = new Casilla[FILAS][COLUMNAS];
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                tablero[fila][columna] = new Casilla();
            }
        }
    }

    private boolean columnaVacia(int columna) {
        return !tablero[0][columna].estaOcupada();
    }

    public boolean estaVacio() {
        for (int columna = 0; columna < COLUMNAS; columna++) {
            if (!columnaVacia(columna)) {
                return false;
            }
        }
        return true;
    }

    private boolean columnaLlena(int columna) {
        return tablero[FILAS - 1][columna].estaOcupada();
    }

    public boolean estaLleno() {
        for (int columna = 0; columna < COLUMNAS; columna++) {
            if (!columnaLlena(columna)) {
                return false;
            }
        }
        return true;
    }

    private void comprobarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new IllegalArgumentException("La ficha no puede ser nula.");
        }
    }

    private void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Columna fuera de rango.");
        }
    }

    private int getPrimeraFilaVacia(int columna) {
        for (int fila = FILAS - 1; fila >= 0; fila--) {
            if (!tablero[fila][columna].estaOcupada()) {
                return fila;
            }
        }
        throw new IllegalArgumentException("La columna está llena.");
    }

    private boolean objetivoAlcanzado(int consecutivas) {
        return consecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int consecutivas = 0;
        for (int columna = 0; columna < COLUMNAS; columna++) {
            if (tablero[fila][columna].getFicha() == ficha) {
                consecutivas++;
                if (objetivoAlcanzado(consecutivas)) {
                    return true;
                }
            } else {
                consecutivas = 0;
            }
        }
        return false;
    }

    private boolean comprobarVertical(int columna, Ficha ficha) {
        int consecutivas = 0;
        for (int fila = 0; fila < FILAS; fila++) {
            if (tablero[fila][columna].getFicha() == ficha) {
                consecutivas++;
                if (objetivoAlcanzado(consecutivas)) {
                    return true;
                }
            } else {
                consecutivas = 0;
            }
        }
        return false;
    }

    private int menor(int a, int b) {
        return Math.min(a, b);
    }

    private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {
        int desplazamiento = menor(fila, columna);
        int filaInicial = fila - desplazamiento;
        int columnaInicial = columna - desplazamiento;
        int consecutivas = 0;

        while (filaInicial < FILAS && columnaInicial < COLUMNAS) {
            if (tablero[filaInicial][columnaInicial].getFicha() == ficha) {
                consecutivas++;
                if (objetivoAlcanzado(consecutivas)) {
                    return true;
                }
            } else {
                consecutivas = 0;
            }
            filaInicial++;
            columnaInicial++;
        }
        return false;
    }

    private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
        int desplazamiento = menor(fila, COLUMNAS - 1 - columna);
        int filaInicial = fila - desplazamiento;
        int columnaInicial = columna + desplazamiento;
        int consecutivas = 0;

        while (filaInicial < FILAS && columnaInicial >= 0) {
            if (tablero[filaInicial][columnaInicial].getFicha() == ficha) {
                consecutivas++;
                if (objetivoAlcanzado(consecutivas)) {
                    return true;
                }
            } else {
                consecutivas = 0;
            }
            filaInicial++;
            columnaInicial--;
        }
        return false;
    }

    public boolean comprobarTirada(int fila, int columna, Ficha ficha) {
        return comprobarHorizontal(fila, ficha) ||
                comprobarVertical(columna, ficha) ||
                comprobarDiagonalNE(fila, columna, ficha) ||
                comprobarDiagonalNO(fila, columna, ficha);
    }

    public boolean introducirFicha(int columna, Ficha ficha) {
        comprobarFicha(ficha);
        comprobarColumna(columna);
        if (columnaLlena(columna)) {
            throw new IllegalArgumentException("La columna está llena.");
        }
        int fila = getPrimeraFilaVacia(columna);
        tablero[fila][columna].setFicha(ficha);
        return comprobarTirada(fila, columna, ficha);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                Ficha ficha = tablero[fila][columna].getFicha();
                sb.append(ficha == null ? "[ ]" : "[" + ficha + "]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
