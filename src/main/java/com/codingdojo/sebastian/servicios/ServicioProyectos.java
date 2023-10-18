package com.codingdojo.sebastian.servicios;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.sebastian.excepciones.PaginaLimiteExcedidoException;
import com.codingdojo.sebastian.modelos.Pagina;
import com.codingdojo.sebastian.modelos.Plantilla;
import com.codingdojo.sebastian.modelos.Proyecto;
import com.codingdojo.sebastian.modelos.Tarea;
import com.codingdojo.sebastian.modelos.Usuario;
import com.codingdojo.sebastian.repositorios.RepositorioPaginas;
import com.codingdojo.sebastian.repositorios.RepositorioProyectos;
import com.codingdojo.sebastian.repositorios.RepositorioTareas;
import com.codingdojo.sebastian.repositorios.RepositorioUsuarios;

@Service
public class ServicioProyectos {

	@Autowired
	private RepositorioUsuarios ru;

    @Autowired 
    private RepositorioProyectos rp;
    
    @Autowired
    private RepositorioTareas rt;
    
    @Autowired
    private RepositorioPaginas rpag;
    
    //*SERVICIOS PROYECTOS*//
    
    //1.-Guardar Proyecto
    public Proyecto guardarProyecto(Proyecto nuevoProyecto) {
        return rp.save(nuevoProyecto);
    }
    //2.-Encontrar Proyecto
    public Proyecto encontrarProyecto(Long id) {
        return rp.findById(id).orElse(null);
    }
    //3.-Eliminar Proyecto
    public void eliminarProyecto(Long id) {
    	 rp.deleteById(id);
    }
    //4.-Lista de todos los proyectos.
    public List <Proyecto> listaTodosLosProyectos(){
    	return rp.findAll();
    }
    //5.-Listar proyectos en base al Usuario.
    public List<Proyecto> listaProyectoPorUsuario(Usuario usuarioEnSesion){
    	
    	Long idUsuario = usuarioEnSesion.getId();
    	
    	return rp.findByCreador_Id(idUsuario);
    }
    //6.- Crear proyectos
    	public Proyecto crearProyectos(Proyecto nuevoProyecto) {			
    	
    	String plantilla = nuevoProyecto.getPlantilla();
    	
    	if(plantilla.equals("estudiante")) {
    		
    		return Plantilla.plantillaEstudianteAutomatica(nuevoProyecto,this);
    		
    	} else if(plantilla.equals("gimnasio")) {
    		return Plantilla.plantillaGimnasioAutomatica(nuevoProyecto,this);
    	}else {
    		return null;
    	}
    }
    	
    	

    //*SERVICIOS PAGINAS*//
    
    //1.-Guardar Pagina
    public Pagina guardarPagina(Pagina nuevaPagina) {
    	return rpag.save(nuevaPagina);
    }
    //2.-Encontrar Pagina
    public Pagina encontrarPagina(Long id) {
    	return rpag.findById(id).orElse(null);
    }
    //3.-Eliminar Pagina
    public void eliminarPagina(Long id) {
    	rpag.deleteById(id);
    }
    //4.- Listar Paginas
    public List<Pagina> listaPagina(){
    	return rpag.findAll();
    }
    
    //5.-Relacionar Paginas y Proyectos
    public Pagina guardarPaginaYRelaciones(Usuario usuarioRelacion, Proyecto proyectoRelacion, Pagina nuevaPagina) {
    	//relacion entre (proyecto/pagina) & (usuario/pagina)
    	nuevaPagina.setProyectoPagina(proyectoRelacion);
    	nuevaPagina.setUsuarioPagina(usuarioRelacion);
    	
    	//Obtiene lista de paginas asociadas al proyecto y agrega la pagina al proyecto.
    	List<Pagina> paginaProyecto = proyectoRelacion.getProyectoPaginas();
    	paginaProyecto.add(nuevaPagina);
    	
    	//Obtiene la lista de paginas asociadas al usuario y agrega la pagina al usuario.
    	List<Pagina> paginaUsuario = usuarioRelacion.getMisPaginas();
    	paginaUsuario.add(nuevaPagina);
    	
    	//Actualiza la lista de páginas  asociadas con el proyecto y el usuario para reflejar los cambios.
    	proyectoRelacion.setProyectoPaginas(paginaProyecto);
    	usuarioRelacion.setMisPaginas(paginaUsuario);
    	
    	rp.save(proyectoRelacion);
    	ru.save(usuarioRelacion);
    	return guardarPagina(nuevaPagina);
    }
    
    //6.-Crear Paginas
    public Pagina crearNuevaPagina(String tipoPagina, String nombre, Long idProyecto, Long idCreador) {
        Usuario obtenerCreador = ru.findById(idCreador).orElse(null);
        Proyecto proyectoDondeSeCreaLaPagina = rp.findById(idProyecto).orElse(null);
        List<Pagina> paginasDelUsuario = proyectoDondeSeCreaLaPagina.getProyectoPaginas();
        
        if(paginasDelUsuario.size() > 5) {
        	
        	throw new PaginaLimiteExcedidoException("El proyecto ya tiene el límite máximo de 5 páginas.");
        }

        if (obtenerCreador == null || proyectoDondeSeCreaLaPagina == null) {
            return null;
        }

        Pagina nuevaPagina = new Pagina();

        // Determinar el nombre de la página
        String nombrePagina = nombre;

        if (nombre == null || nombre.isEmpty()) {
            String nombreDefault = null;
            if (tipoPagina.equals("habitos")) {
                nombreDefault = "Pagina Habitos ";
            } else if (tipoPagina.equals("bloc")) {
                nombreDefault = "Pagina Bloc ";
            }

            if (nombreDefault != null) {
                int i = 1;
                while (true) {
                    String nombrePropuesto = nombreDefault + i;

                    // Verificar si el nombre está repetido
                    boolean nombreRepetido = paginasDelUsuario.stream().anyMatch(p -> p.getNombre().equals(nombrePropuesto));

                    if (!nombreRepetido) {
                        nombrePagina = nombrePropuesto; // Asigna el nombre único
                        break;
                    }

                    i++;
                }
            } else {
                return null; // Tipo de página no válido
            }
        }

        nuevaPagina.setNombre(nombrePagina);
        return guardarPaginaYRelaciones(obtenerCreador, proyectoDondeSeCreaLaPagina, nuevaPagina);
    }


    //*SERVICIOS TAREAS*//
    
    //1.-Guardar Tarea
    public Tarea guardarTarea(Tarea nuevaTarea) {
    	return rt.save(nuevaTarea);
    }
    //2.-Encontrar Tarea por Id
    public Tarea encontrarTarea(Long id) {
    	return rt.findById(id).orElse(null);
    }
    //3- Eliminar Tarea
    public void eliminarTarea(Long id) {
    	rt.deleteById(id);
    }
    //4.-Listar Tareas
    public List <Tarea> listaTarea(){
    	return rt.findAll();
    }
    //5.-Crear Tareas
    public Tarea crearTarea(Long idPagina , String Contenido , String Dia ,String Tipo, LocalDate fechaLimite) {
    	
    	Pagina paginaTarea = rpag.findById(idPagina).orElse(null);
    	if(paginaTarea == null) {
    		
    		return null;
    		
    	} else {
    		
    		List<Tarea> tareasPagina = paginaTarea.getTareasPagina();
    		
    		List<Tarea> tareaProyecto = paginaTarea.getProyectoPagina().getProyectoTareas();
    		
    		List<Tarea> tareaUsuario = paginaTarea.getUsuarioPagina().getMisTareas();
    		
    		Tarea tareaAgregar = new Tarea();
    		tareaAgregar.setContenido(Contenido);
    		tareaAgregar.setDia(Dia);
    		tareaAgregar.setTipo(Tipo);
    		tareaAgregar.setFechaLimite(fechaLimite);
    		tareaAgregar.setPaginaDeTarea(paginaTarea);
    		tareaAgregar.setTareasProyecto(paginaTarea.getProyectoPagina());
    		tareaAgregar.setUsuarioAsignado(paginaTarea.getUsuarioPagina());
    		
    		tareasPagina.add(tareaAgregar);
    		
    		tareaProyecto.add(tareaAgregar);
    		
    		tareaUsuario.add(tareaAgregar);
    		
    		ru.save(paginaTarea.getUsuarioPagina());
    		
    		rp.save(paginaTarea.getProyectoPagina());
    		
    		rpag.save(paginaTarea);
    		
    		return rt.save(tareaAgregar);
    	}
    	
    }

    
}