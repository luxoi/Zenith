package com.codingdojo.sebastian.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.sebastian.modelos.Plantilla;
import com.codingdojo.sebastian.modelos.Proyecto;
import com.codingdojo.sebastian.modelos.Tarea;
import com.codingdojo.sebastian.repositorios.RepositorioProyectos;
import com.codingdojo.sebastian.repositorios.RepositorioTareas;

@Service
public class ServicioProyectos {



    @Autowired 
    private RepositorioProyectos rp;
    
    @Autowired
    private RepositorioTareas rt;
    
    

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
    	
    	List<Tarea> tareas = Plantilla.crearTareaPorProyecto(nuevoProyecto);   //llamamos al metodo de plantilla
    	nuevoProyecto = rp.save(nuevoProyecto);
    	for(Tarea tarea : tareas) {
    		tarea.setTareasProyecto(nuevoProyecto);		//le asignamos a todas las tareas el proyeto que creamos
    	}
    	rt.saveAll(tareas);								//hacemos un saveAll para guardar la lista y guardamos todo
    	return rp.save(nuevoProyecto);			
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
}