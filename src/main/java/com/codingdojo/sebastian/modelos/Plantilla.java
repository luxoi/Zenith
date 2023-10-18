	package com.codingdojo.sebastian.modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.codingdojo.sebastian.servicios.ServicioProyectos;

public class Plantilla {
	
    // Plantilla genérica para crear proyectos con tareas predefinidas
    public static Proyecto crearProyectoConPlantilla(Proyecto nuevoProyecto, ServicioProyectos servicio, String nombrePagina, String descripcionPagina,String tipoPagina, List<String> tareas, String tareasTipo) {
        List<Pagina> listaPagina = new ArrayList<>();
        List<Tarea> tareasParaProyecto = new ArrayList<>();
        Pagina pagina = new Pagina();
        pagina.setNombre(nombrePagina);
        pagina.setDescripcion(descripcionPagina);
        pagina.setTipoPagina(tipoPagina);

        for (String dia : Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")) {
            for (String tareaContenido : tareas) {
                Tarea tarea = new Tarea();
                tarea.setContenido(tareaContenido);
                tarea.setTipo(tareasTipo);
                tarea.setDia(dia);
                tarea.setUsuarioAsignado(nuevoProyecto.getCreador());
                tarea.setPaginaDeTarea(pagina);
                tarea.setTareasProyecto(nuevoProyecto);
                tareasParaProyecto.add(tarea);
            }
        }

        pagina.setTareasPagina(tareasParaProyecto);
        pagina.setUsuarioPagina(nuevoProyecto.getCreador());
        pagina.setProyectoPagina(nuevoProyecto);
        listaPagina.add(pagina);
        nuevoProyecto.setProyectoPaginas(listaPagina);
        nuevoProyecto.setProyectoTareas(tareasParaProyecto);

        servicio.guardarProyecto(nuevoProyecto);
        for (Pagina paginas : listaPagina) {
            servicio.guardarPagina(paginas);
        }
        for (Tarea tarea : tareasParaProyecto) {
            servicio.guardarTarea(tarea);
        }

        return nuevoProyecto;
    }

    // Plantilla específica para estudiantes
    public static Proyecto plantillaEstudianteAutomatica(Proyecto nuevoProyecto, ServicioProyectos servicio) {
        String nombrePagina = "Habitos semana";
        String descripcionPagina = "En este apartado podrás crear los hábitos para la semana, podrás cambiarlos y actualizarlos a tu gusto. ¡Mucha suerte!";
        List<String> tareas = Arrays.asList("Asistir a clases", "Estudiar", "Hacer tareas", "Prepararse para el examen", "Tomarse un descanso");
        return crearProyectoConPlantilla(nuevoProyecto, servicio, nombrePagina, descripcionPagina,"habitos", tareas, "checkbox" );
    }

    // Plantilla específica para gimnasio
    public static Proyecto plantillaGimnasioAutomatica(Proyecto nuevoProyecto, ServicioProyectos servicio) {
        String nombrePagina = "Habitos semana";
        String descripcionPagina = "En este apartado podrás crear los hábitos para la semana, podrás cambiarlos y actualizarlos a tu gusto. ¡Mucha suerte!";
        List<String> tareas = Arrays.asList("Realizar Estiramientos", "Entrenar hoy", "Dieta de hoy!", "Meditación", "Sueño Reparador");
        return crearProyectoConPlantilla(nuevoProyecto, servicio, nombrePagina, descripcionPagina,"habitos", tareas, "checkbox");
    }
    
}

