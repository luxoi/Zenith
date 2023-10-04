package com.codingdojo.sebastian.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.sebastian.modelos.Proyecto;
import com.codingdojo.sebastian.modelos.Usuario;
import com.codingdojo.sebastian.repositorios.RepositorioProyectos;

@Service
public class ServicioProyectos {



    @Autowired 
    private RepositorioProyectos rp;

    //Proyectos
    public Proyecto guardarProyecto(Proyecto nuevoProyecto) {
        return rp.save(nuevoProyecto);
    }
    
    public Proyecto encontrarProyecto(Long id) {
        return rp.findById(id).orElse(null);
    }

    public void eliminarProyecto(Long id) {
    	 rp.deleteById(id);
    }

    public List <Proyecto> listaProyectos(){
    	return rp.findAll();
    }
    
}