package com.codingdojo.sebastian.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plantilla {
		
	public static List<Tarea> crearTareaPorProyecto(Proyecto nuevoProyecto) { //este metodo estatico extraera el tipo de plantilla del proyecto y basado en la plantilla que sea crear una tareas automaticas
		String plantilla = nuevoProyecto.getPlantilla();
		List<Tarea> listaTarea = new ArrayList<>();  
		if (plantilla.equals("estudiante")) {    //la primera plantilla es la de estudiante
	        LocalDate fechaLimite = LocalDate.now(); // Obtener la fecha actual
	        
	        for (int i = 1; i <= 7; i++) {		//creara 7 tareas de Estudiar para una semana (incluye fecha limite)
	            Tarea tarea = new Tarea();
	            tarea.setContenido("Estudiar");
	            tarea.setTipo("lista");			//tipo de tarea que se va a crear
	            tarea.setUsuarioAsignado(nuevoProyecto.getCreador());
	            tarea.setTareasProyecto(nuevoProyecto);
	            
	            // Asignar la fecha límite en función del valor de 'i'
	            tarea.setFechaLimite(fechaLimite.plusDays(i));
	            
	            listaTarea.add(tarea);		
	        }
	        for (int i = 0; i < 3; i++) {			//crea 3 tareas de Prueba sin fecha limite
	            Tarea tarea = new Tarea();
	            tarea.setContenido("checkbox");		
	            tarea.setTipo("otroTipo"); // Ajusta el tipo según tus necesidades
	            tarea.setUsuarioAsignado(nuevoProyecto.getCreador());
	            tarea.setTareasProyecto(nuevoProyecto);
	            
	            listaTarea.add(tarea);
	        }

	        // Crear una tarea de "trabajo grupal" sin fecha
	        Tarea tareaTrabajoGrupal = new Tarea();		//crea 1 tarea de trabajo grupal
	        tareaTrabajoGrupal.setContenido("Trabajo Grupal");
	        tareaTrabajoGrupal.setTipo("recordatorio"); // Ajusta el tipo según tus necesidades
	        tareaTrabajoGrupal.setUsuarioAsignado(nuevoProyecto.getCreador());
	        tareaTrabajoGrupal.setTareasProyecto(nuevoProyecto);
	        
	        listaTarea.add(tareaTrabajoGrupal);
			return listaTarea;		//retornamos la lista
		} else {
			return null;			
		}
	}
}
