package com.biblioteca.models;

import java.util.ArrayList;

public class Catalogo {
    private ArrayList<Libro> libros;
    public Catalogo() {
        this.libros = new ArrayList<>();
    }
    public Catalogo(ArrayList<Libro> libros) {
        this.libros = libros;
    }


    public ArrayList<Libro> obtenerLibros() {
        return this.libros;
    }
    public void ponerLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
    }

    public Libro buscarPorIsbn(String isbn) {
        for (Libro libro : this.libros) {
            if (libro.getIsbn().equals(isbn)) {
                System.out.println("Libro encontrado: " + libro.getTitulo());
                return libro;
            }
        }
        System.out.println("Libro no encontrado con ISBN: " + isbn);
        return null;
    }

    public  ArrayList<Libro> obtenerLibrosDisponibles() {
        ArrayList<Libro> librosDisponibles = new ArrayList<>();
        for (Libro libro : this.libros) {
            if (libro.getEstado() == Estado.DISPONIBLE) {
                librosDisponibles.add(libro);
            }
        }
        return librosDisponibles;
    }

}
