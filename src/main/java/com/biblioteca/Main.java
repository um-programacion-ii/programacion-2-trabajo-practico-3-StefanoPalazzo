package com.biblioteca;

import com.biblioteca.models.Estado;
import com.biblioteca.models.Libro;

public class Main {
    public static void main(String[] args) {
        Libro libro1 = new Libro("978-3-16-148410-0", "El Gran Libro", "Juan Pérez");

        libro1.mostrarDetalles();

        libro1.setEstado(Estado.PRESTADO);

        System.out.println("Después de cambiar el estado:");
        libro1.mostrarDetalles();
    }
}
