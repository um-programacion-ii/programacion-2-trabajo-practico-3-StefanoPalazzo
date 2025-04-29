package com.biblioteca.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int dni;
    private String nombre;
    private List<Prestamo> historialPrestamos;

    public Usuario(int dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.historialPrestamos = new ArrayList<>();
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Prestamo> getHistorialPrestamos() {
        return historialPrestamos;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo != null) {
            historialPrestamos.add(prestamo);
        } else {
            throw new IllegalArgumentException("El préstamo no puede ser nulo.");
        }
    }
}
