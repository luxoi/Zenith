package com.codingdojo.sebastian.servicios;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.sebastian.modelos.Pagina;
import com.codingdojo.sebastian.modelos.Plantilla;
import com.codingdojo.sebastian.modelos.Proyecto;
import com.codingdojo.sebastian.modelos.Tarea;
import com.codingdojo.sebastian.repositorios.RepositorioPaginas;
import com.codingdojo.sebastian.repositorios.RepositorioProyectos;
import com.codingdojo.sebastian.repositorios.RepositorioTareas;

@Service
public class ServicioProyectos {



    @Autowired 
    private RepositorioProyectos rp;
    
    @Autowired
    private RepositorioTareas rt;
    
    @Autowired
    private RepositorioPaginas rpag;
    
    

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
    
    public Proyecto crearProyectos(Proyecto nuevoProyecto) {			//servicio para crear proyectos
    	
    	String plantilla = nuevoProyecto.getPlantilla();
    	
    	if(plantilla.equals("estudiante")) {
    		
    		return Plantilla.plantillaEstudianteAutomatica(nuevoProyecto,this);
    		
    	} else if(plantilla.equals("gimnasio")) {
    		return Plantilla.plantillaGimnasioAutomatica(nuevoProyecto,this);
    	}else {
    		return null;
    	}
    }
    
    public Tarea guardarTarea(Tarea nuevaTarea) {
    	return rt.save(nuevaTarea);
    }
    
    public Tarea encontrarTarea(Long id) {
    	return rt.findById(id).orElse(null);
    }
    
    public void eliminarTarea(Long id) {
    	rt.deleteById(id);
    }
    
    public List <Tarea> listaTarea(){
    	return rt.findAll();
    }
    
    public Pagina guardarPagina(Pagina nuevaPagina) {
    	return rpag.save(nuevaPagina);
    }
    
    public Pagina encontrarPagina(Long id) {
    	return rpag.findById(id).orElse(null);
    }
    
    public void eliminarPagina(Long id) {
    	rpag.deleteById(id);
    }
    
    public List<Pagina> listaPagina(){
    	return rpag.findAll();
    }
}