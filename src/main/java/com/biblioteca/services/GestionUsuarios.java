package com.biblioteca.services;

import com.biblioteca.models.Catalogo;
import com.biblioteca.models.Prestamo;
import com.biblioteca.models.Usuario;
import com.biblioteca.services.SistemaPrestamos;

import java.util.HashMap;
import java.util.Map;

public class GestionUsuarios {
    private Map<Integer, Usuario> usuarios = new HashMap<>();
    private SistemaPrestamos sistemaPrestamos;
    private Catalogo catalogo;

    public GestionUsuarios(SistemaPrestamos sistemaPrestamos, Catalogo catalogo) {
        this.sistemaPrestamos = sistemaPrestamos;
        this.catalogo = catalogo;
    }

    public void registrarUsuario(int dni, String nombre) {
        if (!usuarios.containsKey(dni)) {
            usuarios.put(dni, new Usuario(dni, nombre));
        } else {
            throw new IllegalArgumentException("El usuario ya está registrado.");
        }
    }

    public Usuario obtenerUsuario(int dni) {
        return usuarios.get(dni);
    }

    public void registrarPrestamo(int dni, String isbn) {
        Usuario usuario = usuarios.get(dni);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no registrado.");
        }

        Prestamo prestamo = sistemaPrestamos.prestarLibro(isbn);
        if (prestamo == null) {
            throw new IllegalStateException("No se pudo realizar el préstamo.");
        }
        usuario.agregarPrestamo(prestamo);
    }
}
