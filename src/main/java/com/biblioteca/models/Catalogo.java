package com.biblioteca.models;

import java.util.ArrayList;

public class Catalogo {
    private static ArrayList<Libro> libros;
    public Catalogo() {
        libros = new ArrayList<Libro>();
    }
    public Catalogo(ArrayList<Libro> libros) {
        Catalogo.libros = libros;
    }


    public static ArrayList<Libro> obtenerLibros() {
        return libros;
    }
    public static void ponerLibros(ArrayList<Libro> libros) {
        Catalogo.libros = libros;
    }
    public static void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public static Libro buscarPorIsbn(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                System.out.println("Libro encontrado: " + libro.getTitulo());
                return libro;
            }
        }
        System.out.println("Libro no encontrado con ISBN: " + isbn);
        return null;
    }

    public static ArrayList<Libro> obtenerLibrosDisponibles() {
        ArrayList<Libro> librosDisponibles = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getEstado() == Estado.DISPONIBLE) {
                librosDisponibles.add(libro);
            }
        }
        return librosDisponibles;
    }

}
