package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Objects;

public record Jugador(String nombre, Ficha colorFichas) {

    public Jugador(String nombre, Ficha colorFichas) {
        this.nombre = validarNombre(nombre);
        this.colorFichas = validarColorFichas(colorFichas);
    }

    private String validarNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        return nombre.trim();
    }

    private Ficha validarColorFichas(Ficha colorFichas) {
        Objects.requireNonNull(colorFichas, "El color de las fichas no puede ser nulo.");
        return colorFichas;
    }

    @Override
    public String toString() {
        return String.format("Jugador[nombre='%s', colorFichas=%s]", nombre, colorFichas);
    }
}
