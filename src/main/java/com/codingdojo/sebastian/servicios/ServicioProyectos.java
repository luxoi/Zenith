package com.codingdojo.sebastian.servicios;



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

    public List <Proyecto> listaTodosLosProyectos(){
    	return rp.findAll();
    }
    
    public List<Proyecto> listaProyectoPorUsuario(Usuario usuarioEnSesion){
    	
    	Long idUsuario = usuarioEnSesion.getId();
    	
    	return rp.findByCreador_Id(idUsuario);
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
    
    public Pagina guardarPagina(Pagina nuevaPagina) {
    	return rpag.save(nuevaPagina);
    }
    
    public Pagina guardarPaginaYRelaciones(Usuario usuarioRelacion, Proyecto proyectoRelacion, Pagina nuevaPagina) {
    	
    	nuevaPagina.setProyectoPagina(proyectoRelacion);
    	nuevaPagina.setUsuarioPagina(usuarioRelacion);
    	
    	List<Pagina> paginaProyecto = proyectoRelacion.getProyectoPaginas();
    	paginaProyecto.add(nuevaPagina);
    	
    	List<Pagina> paginaUsuario = usuarioRelacion.getMisPaginas();
    	paginaUsuario.add(nuevaPagina);
    	
    	proyectoRelacion.setProyectoPaginas(paginaProyecto);
    	usuarioRelacion.setMisPaginas(paginaUsuario);
    	
    	rp.save(proyectoRelacion);
    	ru.save(usuarioRelacion);
    	return guardarPagina(nuevaPagina);
    }
    
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


    public Pagina encontrarPagina(Long id) {
    	return rpag.findById(id).orElse(null);
    }
    
    public void eliminarPagina(Long id) {
    	rpag.deleteById(id);
    }
    
    public List<Pagina> listaPagina(){
    	return rpag.findAll();
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