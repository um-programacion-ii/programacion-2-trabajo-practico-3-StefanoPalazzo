package com.biblioteca.tests;

import com.biblioteca.models.*;
import com.biblioteca.services.SistemaPrestamos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.biblioteca.models.Libro;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SistemaPrestamosTest {

    @Mock
    private Catalogo catalogo;

    @InjectMocks
    private SistemaPrestamos sistemaPrestamos;

    @Test
    void testPrestarLibro() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0", "1");

        assertNotNull(prestamo);
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    @Test
    void testPrestarLibroInexistente() {
        when(catalogo.buscarPorIsbn("999-9-99-999999-9")).thenReturn(null);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("999-9-99-999999-9", "1");

        assertNull(prestamo);
        verify(catalogo).buscarPorIsbn("999-9-99-999999-9");
    }

    @Test
    void testPrestarLibroYaPrestado() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro.setEstado(Estado.PRESTADO);
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0", "1");

        assertNull(prestamo);
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
    }
}
