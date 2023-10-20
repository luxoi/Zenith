	package com.codingdojo.sebastian.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.codingdojo.sebastian.servicios.ServicioProyectos;

public class Plantilla {
    public static Proyecto crearProyectoConPlantilla(
        Proyecto nuevoProyecto,
        ServicioProyectos servicio,
        String nombrePagina,
        String descripcionPagina,
        String tipoPagina,
        List<String> tareas,
        List<String> estados, 
        List<String> etiquetas, 
        LocalDate fechaCreacion,
        LocalDate fechaLimite
    ) {
        List<Pagina> listaPagina = new ArrayList<>();
        List<Tarea> tareasParaProyecto = new ArrayList<>();

        // Seteo de parámetros para página
        Pagina pagina = new Pagina();
        pagina.setNombre(nombrePagina);
        pagina.setDescripcion(descripcionPagina);
        pagina.setTipoPagina(tipoPagina);
        

        // Seteo de parámetros para tareas
        for (String dia : Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Vie	rnes")) {
            for (int i = 0; i < tareas.size(); i++) {
                String tareaContenido = tareas.get(i);
                String tareaEstado = estados.get(i);
                String tareaTag = etiquetas.get(i); // Obtén la etiqueta correspondiente

                Tarea tarea = new Tarea();
                tarea.setContenido(tareaContenido);

                tarea.setDia(dia);
                tarea.setUsuarioAsignado(nuevoProyecto.getCreador());
                tarea.setPaginaDeTarea(pagina);
                tarea.setTareasProyecto(nuevoProyecto);
                tarea.setTag(tareaTag); // Asigna la etiqueta a la tarea
                tarea.setEstado(tareaEstado);
                tarea.setFechaCreacion(fechaCreacion);
                tarea.setFechaLimite(fechaLimite);

                tareasParaProyecto.add(tarea);
            }
        }

        // Estructura y relación de páginas, tareas y proyectos
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
        String nombrePagina = "Hábitos semana";
        String descripcionPagina = "En este apartado podrás crear los hábitos para la semana, podrás cambiarlos y actualizarlos a tu gusto. ¡Mucha suerte!";
        List<String> tareas = Arrays.asList("Asistir a clases", "Estudiar", "Hacer tareas", "Prepararse para el examen", "Tomarse un descanso");
        List<String> estados = Arrays.asList("En proceso", "Sin Empezar", "Completado", "En proceso", "Sin Empezar");
        List<String> etiquetas = Arrays.asList("Estudios", "Estudios", "Estudios", "Examenes", "Vacaciones"); // Asigna etiquetas a las tareas

        LocalDate fechaCreacion = LocalDate.now();
        LocalDate fechaLimite = LocalDate.now().plusDays(7);

        return crearProyectoConPlantilla(nuevoProyecto, servicio, nombrePagina, descripcionPagina, "habitos", tareas, estados, etiquetas, fechaCreacion, fechaLimite);
    }
}

