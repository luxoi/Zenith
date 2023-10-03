package com.codingdojo.sebastian.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.sebastian.modelos.Proyecto;
import com.codingdojo.sebastian.modelos.Usuario;
import com.codingdojo.sebastian.repositorios.RepositorioProyectos;
import com.codingdojo.sebastian.repositorios.RepositorioUsuarios;

@Service
public class ServicioProyectos {

    @Autowired
    private RepositorioUsuarios ru;

    @Autowired 
    private RepositorioProyectos rp;

    public Proyecto guardarProyecto(Proyecto nuevoProyecto) {
        return rp.save(nuevoProyecto);
    }

    public Usuario encontrarUsuario(Long id) {
        return ru.findById(id).orElse(null);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return ru.save(usuario);
    }
    
    public Proyecto encontrarProyecto(Long id) {
        return rp.findById(id).orElse(null);
    }


}