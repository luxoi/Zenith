package com.codingdojo.sebastian.modelos;

import java.util.ArrayList;
import java.util.List;

import com.codingdojo.sebastian.servicios.ServicioProyectos;

public class Plantilla {
	
	public static Proyecto plantillaEstudianteAutomatica(Proyecto nuevoProyecto,ServicioProyectos servicio) { //este metodo estatico extraera el tipo de plantilla del proyecto y basado en la plantilla que sea crear una tareas automaticas
		List<Pagina> listaPagina = new ArrayList<>();  
		Pagina paginaHabitos = new Pagina();
		paginaHabitos.setNombre("Habitos semana");
		paginaHabitos.setDescripcion("En este apartado podras crear los habitos para la semana , podras cambiarlos y actualizarlos a tu gusto.¡Mucha suerte!");
		List<Tarea> tareasParaHabitos = new ArrayList<>();
		for(int i=0 ; i<5 ; i++) {
			Tarea tareaIrAClases = new Tarea();
			tareaIrAClases.setContenido("Asistir a clases");
			tareaIrAClases.setTipo("checkbox");
			
			Tarea tareaEstudiar = new Tarea();
			tareaEstudiar.setContenido("Estudiar");
			tareaEstudiar.setTipo("checkbox");
			
			Tarea tareaHacerTareas = new Tarea();
			tareaHacerTareas.setContenido("Hacer tareas");
			tareaHacerTareas.setTipo("checkbox");
			
			Tarea tareaPrepararParaExamen = new Tarea();
			tareaPrepararParaExamen.setContenido("Prepararse para el examen");
			tareaPrepararParaExamen.setTipo("checkbox");
			
			Tarea tareaDescanso = new Tarea();
			tareaDescanso.setContenido("Tomarse un descanso");
			tareaDescanso.setTipo("checkbox");
			
			if(i == 0) {
				tareaIrAClases.setDia("Lunes");
				tareasParaHabitos.add(tareaIrAClases);
				
				tareaEstudiar.setDia("Lunes");
				tareasParaHabitos.add(tareaEstudiar);
				
				tareaHacerTareas.setDia("Lunes");
				tareasParaHabitos.add(tareaHacerTareas);
				
				tareaPrepararParaExamen.setDia("Lunes");
				tareasParaHabitos.add(tareaPrepararParaExamen);
				
				tareaDescanso.setDia("Lunes");
				tareasParaHabitos.add(tareaDescanso);
				
			} else if(i == 1) {
				tareaIrAClases.setDia("Martes");
				tareasParaHabitos.add(tareaIrAClases);
				
				tareaEstudiar.setDia("Martes");
				tareasParaHabitos.add(tareaEstudiar);
				
				tareaHacerTareas.setDia("Martes");
				tareasParaHabitos.add(tareaHacerTareas);
				
				tareaPrepararParaExamen.setDia("Martes");
				tareasParaHabitos.add(tareaPrepararParaExamen);
				
				tareaDescanso.setDia("Martes");
				tareasParaHabitos.add(tareaDescanso);
				
			} else if(i == 2) {
				tareaIrAClases.setDia("Miercoles");
				tareasParaHabitos.add(tareaIrAClases);
				
				tareaEstudiar.setDia("Miercoles");
				tareasParaHabitos.add(tareaEstudiar);
				
				tareaHacerTareas.setDia("Miercoles");
				tareasParaHabitos.add(tareaHacerTareas);
				
				tareaPrepararParaExamen.setDia("Miercoles");
				tareasParaHabitos.add(tareaPrepararParaExamen);
				
				tareaDescanso.setDia("Miercoles");
				tareasParaHabitos.add(tareaDescanso);
				
			} else if(i == 3) {
				tareaIrAClases.setDia("Jueves");
				tareasParaHabitos.add(tareaIrAClases);
				
				tareaEstudiar.setDia("Jueves");
				tareasParaHabitos.add(tareaEstudiar);
				
				tareaHacerTareas.setDia("Jueves");
				tareasParaHabitos.add(tareaHacerTareas);
				
				tareaPrepararParaExamen.setDia("Jueves");
				tareasParaHabitos.add(tareaPrepararParaExamen);
				
				tareaDescanso.setDia("Jueves");
				tareasParaHabitos.add(tareaDescanso);
				
			} else {
				tareaIrAClases.setDia("Viernes");
				tareasParaHabitos.add(tareaIrAClases);
				
				tareaEstudiar.setDia("Viernes");
				tareasParaHabitos.add(tareaEstudiar);
				
				tareaHacerTareas.setDia("Viernes");
				tareasParaHabitos.add(tareaHacerTareas);
				
				tareaPrepararParaExamen.setDia("Viernes");
				tareasParaHabitos.add(tareaPrepararParaExamen);
				
				tareaDescanso.setDia("Viernes");
				tareasParaHabitos.add(tareaDescanso);
			} 
		}
		for(Tarea tareas:tareasParaHabitos) {
			tareas.setUsuarioAsignado(nuevoProyecto.getCreador());
			tareas.setPaginaDeTarea(paginaHabitos);
			tareas.setTareasProyecto(nuevoProyecto);
		}
		paginaHabitos.setTareasPagina(tareasParaHabitos);
		paginaHabitos.setUsuarioPagina(nuevoProyecto.getCreador());
		paginaHabitos.setProyectoPagina(nuevoProyecto);
		listaPagina.add(paginaHabitos);
		nuevoProyecto.setProyectoPaginas(listaPagina);
		nuevoProyecto.setProyectoTareas(tareasParaHabitos);
		
		servicio.guardarProyecto(nuevoProyecto);
		
		for(Pagina paginas:listaPagina) {
			servicio.guardarPagina(paginas);
		}
		
		for(Tarea tareas:tareasParaHabitos) {
			servicio.guardarTarea(tareas);
		}
		
		return nuevoProyecto;
	}
}
