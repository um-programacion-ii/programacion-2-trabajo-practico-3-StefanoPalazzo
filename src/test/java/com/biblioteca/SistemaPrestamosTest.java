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

        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0");

        assertNotNull(prestamo);
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    @Test
    void testPrestarLibroInexistente() {
        when(catalogo.buscarPorIsbn("999-9-99-999999-9")).thenReturn(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            sistemaPrestamos.prestarLibro("999-9-99-999999-9");
        });

        assertEquals("El libro no existe.", ex.getMessage());
    }


    @Test
    void testPrestarLibroYaPrestado() {
        Libro libro = new Libro("Clean Code", "Robert C. Martin","978-3-16-148410-0");
        libro.setEstado(Estado.PRESTADO); // MUY IMPORTANTE
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            sistemaPrestamos.prestarLibro("978-3-16-148410-0");
        });

        assertEquals("El libro ya está prestado.", ex.getMessage());
    }



}
