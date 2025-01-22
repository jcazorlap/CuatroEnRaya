package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Casilla {
    private Ficha ficha;

    public Casilla() {
        this.ficha = null;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        if (this.ficha != null) {
            throw new IllegalStateException("La casilla ya está ocupada.");
        }
        this.ficha = ficha;
    }

    public boolean estaOcupada() {
        return ficha != null;
    }

    @Override
    public String toString() {
        return ficha == null ? "[vacía]" : String.format("[ficha=%s]", ficha);
    }
}

