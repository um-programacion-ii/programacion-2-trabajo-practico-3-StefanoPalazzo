package com.biblioteca;

import com.biblioteca.models.Libro;
import com.biblioteca.models.Catalogo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class CatalogoTest {
    private Catalogo catalogo;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
        Libro libro1 = new Libro( "Clean Code", "Robert C. Martin", "978-3-16-148410-0");
        Libro libro2 = new Libro("Clean Architecture", "Robert C. Martin", "978-0-13-235088-4");
        catalogo.agregarLibro(libro1);
        catalogo.agregarLibro(libro2);
    }

    @Test
    void testBuscarPorIsbn() {
        Libro libro = catalogo.buscarPorIsbn("978-3-16-148410-0");
        assertNotNull(libro);
        assertEquals("Clean Code", libro.getTitulo());
    }

    @Test
    void testBusquedaFallida(){
        Libro libro = catalogo.buscarPorIsbn("111-2-33-444444-5");
        assertNull(libro);
    }

    @Test
    void testAgregarLibros()  {
        Libro libro3 = new Libro("Principito", "Antoine de Saint-Exupéry", "978-3-16-148410-0");
        Libro libro4 = new Libro("El Hobbit", "J.R.R. Tolkien", "978-0-261-10221-7");
        catalogo.agregarLibro(libro3);
        catalogo.agregarLibro(libro4);
        assertEquals(4, catalogo.obtenerLibros().size());
    }


}
