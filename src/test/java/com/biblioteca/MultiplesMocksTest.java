package com.biblioteca;

import com.biblioteca.models.*;
import com.biblioteca.services.GestionUsuarios;
import com.biblioteca.services.SistemaPrestamos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MultiplesMocksTest {

    @Mock
    private Catalogo catalogo;

    @Mock
    private SistemaPrestamos sistemaPrestamos;

    @InjectMocks
    private GestionUsuarios gestionUsuarios;

    @Test
    void testRegistrarPrestamo() {

        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        Prestamo prestamo = new Prestamo(libro);

        when(sistemaPrestamos.prestarLibro("978-3-16-148410-0")).thenReturn(prestamo);

        gestionUsuarios.registrarUsuario(21654987,"Stefano");
        gestionUsuarios.registrarPrestamo(21654987, "978-3-16-148410-0");

        Usuario usuario = gestionUsuarios.obtenerUsuario(21654987);
        verify(sistemaPrestamos).prestarLibro("978-3-16-148410-0");
        assertEquals(1, usuario.getHistorialPrestamos().size());
    }

    @Test
    void testPrestamoUsuarioNoRegistrado() {
        String isbn = "978-3-16-148410-0";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            gestionUsuarios.registrarPrestamo(99999999, isbn);
        });

        assertEquals("Usuario no registrado.", ex.getMessage());
    }

    @Test
    void testPrestamoLibroYaPrestado() {
        String isbn = "978-3-16-148410-0";
        gestionUsuarios.registrarUsuario(21654987, "Stefano");

        when(sistemaPrestamos.prestarLibro(isbn)).thenThrow(new IllegalStateException("El libro ya está prestado."));

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            gestionUsuarios.registrarPrestamo(21654987, isbn);
        });

        assertEquals("El libro ya está prestado.", ex.getMessage());
    }


}
