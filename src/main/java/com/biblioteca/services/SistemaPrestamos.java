package com.biblioteca.services;

import com.biblioteca.models.Catalogo;
import com.biblioteca.models.Estado;
import com.biblioteca.models.Libro;
import com.biblioteca.models.Prestamo;

import java.util.ArrayList;
import java.util.List;

public class SistemaPrestamos {

    private List<Prestamo> prestamos = new ArrayList<>();
    private Catalogo catalogo;

    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Prestamo prestarLibro(String isbn) {
        Libro libro = catalogo.buscarPorIsbn(isbn);
        if (libro == null) {
            throw new IllegalArgumentException("El libro no existe.");
        }
        if (libro.getEstado() != Estado.DISPONIBLE) {
            throw new IllegalStateException("El libro ya está prestado.");
        }

        libro.setEstado(Estado.PRESTADO);
        Prestamo prestamo = new Prestamo(libro);
        registrarPrestamo(prestamo);

        return prestamo;
    }

    public void registrarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public List<Prestamo> obtenerPrestamos() {
        return prestamos;
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
        throw new IllegalArgumentException("El libro no está prestado.");
    }
}
