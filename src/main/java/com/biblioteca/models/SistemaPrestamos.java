package com.biblioteca.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaPrestamos {

    private List<Prestamo> prestamos = new ArrayList<>();
    private Catalogo catalogo;

    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Prestamo prestarLibro(String isbn, String usuarioId) {
        Libro libro = catalogo.buscarPorIsbn(isbn);
        if (libro == null || libro.getEstado() == Estado.PRESTADO) {
            System.out.println("El libro no está disponible para préstamo.");
            return null;
        }

        libro.setEstado(Estado.PRESTADO);
        Prestamo prestamo = new Prestamo(libro, usuarioId);
        registrarPrestamo(prestamo);

        return prestamo;
    }

    public void registrarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public List<Prestamo> obtenerPrestamos() {
        return prestamos;
    }

    public boolean estaPrestado(String isbn) {
        return prestamos.stream()
                .anyMatch(p -> p.getLibro().getIsbn().equals(isbn));
    }

    public void devolverLibro(String isbn) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().getIsbn().equals(isbn)) {
                prestamo.getLibro().setEstado(Estado.DISPONIBLE);
                prestamos.remove(prestamo);
                System.out.println("El libro ha sido devuelto.");
                return;
            }
        }
        System.out.println("No se encontró el libro en los préstamos.");
    }
}
