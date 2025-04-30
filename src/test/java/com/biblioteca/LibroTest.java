package com.biblioteca;

import com.biblioteca.models.Libro;
import com.biblioteca.models.Estado; // Asegúrate de importar la clase Estado
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;

class LibroTest {

    @Test
    void testCrearLibroValido() {
        Libro libro = new Libro("Clean Code", "Robert C. Martin", "978-3-16-148410-0" );

        assertEquals("978-3-16-148410-0", libro.getIsbn());
        assertEquals("Clean Code", libro.getTitulo());
        assertEquals("Robert C. Martin", libro.getAutor());
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }

    @Test
    void testCambiarEstadoLibro() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");

        libro.setEstado(Estado.PRESTADO);

        assertEquals(Estado.PRESTADO, libro.getEstado());
    }
}

