package com.biblioteca.models;
import java.time.LocalDate;

public class Prestamo {
    private Libro libro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String usuario;

    public Prestamo(Libro libro, String usuario) {
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = LocalDate.now().plusDays(7);
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
