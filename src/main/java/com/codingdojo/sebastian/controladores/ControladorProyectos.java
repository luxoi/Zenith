package com.codingdojo.sebastian.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.sebastian.modelos.Pagina;
import com.codingdojo.sebastian.modelos.Proyecto;
import com.codingdojo.sebastian.modelos.Usuario;
import com.codingdojo.sebastian.servicios.Servicio;
import com.codingdojo.sebastian.servicios.ServicioProyectos;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorProyectos {

	@Autowired
	private Servicio su;
	
	@Autowired
	private ServicioProyectos sp;
	
	@GetMapping("/dashboard")
    public String dashboard(HttpSession session,
    						 @ModelAttribute("nuevaProyecto") Proyecto NuevoProyecto,
    						 Model model) {

		//Verificar usuario en sesion//
        Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
        if(usuarioTemporal == null) {
            return "redirect:/";
        }
        //Verificar usuario en sesion//
        
        List<Proyecto> proyectos = sp.listaProyectoPorUsuario(usuarioTemporal);
        model.addAttribute("proyectos", proyectos);

        return "dashboard.jsp";
    }
	
	@GetMapping("/crear/proyectos")
	public String crearProyectos(HttpSession session ,@ModelAttribute("nuevoProyecto") Proyecto nuevoProyecto ) {
		
		//Verificar usuario en sesion//
        Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
        if(usuarioTemporal == null) {
            return "redirect:/";
        }
        //Verificar usuario en sesion//
        return "formularioproyecto.jsp";
		
	}
	
	@PostMapping("/crearProyecto")
	public String crearProyecto(HttpSession session,
								@Valid @ModelAttribute("nuevoProyecto") Proyecto nuevoProyecto,
								Model model,BindingResult result) {
		//Verificar usuario en sesion//
        Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
        if(usuarioTemporal == null) {
            return "redirect:/";
        }
        if(result.hasErrors()) {
            
            List<Proyecto> proyectos = sp.listaProyectoPorUsuario(usuarioTemporal);
            model.addAttribute("proyectos", proyectos);

            return "dashboard.jsp";
        }
        
        	// guardar proyecto nuevo
        	sp.crearProyectos(nuevoProyecto);
        	// Obtener la lista de proyectos actualizada
        	List<Proyecto> proyectos = sp.listaProyectoPorUsuario(usuarioTemporal);
        	// Agregar la lista actualizada de proyectos al modelo
        	model.addAttribute("proyectos", proyectos);
	       
        	// Redireccion a habitos por primera vez
        	List<Pagina> listaPaginasUsuario = nuevoProyecto.getProyectoPaginas();
        	for(Pagina pagina:listaPaginasUsuario) {
        		if(pagina.getTipoPagina().equals("habitos")) {
        			return "redirect:/paginas/"+pagina.getId();
        		}
        	}
        	return "redirect:/dashboard";
	}

	@GetMapping("/proyectos")
	public String proyectos(HttpSession session,Model model) {
		
		//Verificar usuario en sesion//
        Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
        if(usuarioTemporal == null) {
            return "redirect:/";
        }
        //Verificar usuario en sesion//

        List<Proyecto> proyectosUsuario = sp.listaProyectoPorUsuario(usuarioTemporal);
        
        model.addAttribute("proyecto",proyectosUsuario);
		return "proyects.jsp";
	}
	
	@GetMapping("/proyectos/{proyectoId}")
	public String paginaProyectos(HttpSession session,Model model,@PathVariable("proyectoId") Long proyectoId) {
		// Verificar que el usuario esté en sesión
		Usuario usuarioTemporal = (Usuario) session.getAttribute("usuarioEnSesion");
		   if (usuarioTemporal == null) {
		       return "redirect:/";  
		   }
		   
		Proyecto proyectoQueMostrar = sp.encontrarProyecto(proyectoId);
		model.addAttribute("proyectoMostrar",proyectoQueMostrar);
		
		return "paginas.jsp";
		
	}
	
	@GetMapping("/paginas/{paginaId}")
	public String irAPrimeraPagina(@PathVariable("paginaId") Long paginaId, HttpSession session, Model model) {
	    // Verificar que el usuario esté en sesión
	    Usuario usuarioTemporal = (Usuario) session.getAttribute("usuarioEnSesion");
	    if (usuarioTemporal == null) {
	        return "redirect:/";  
	    }
	    // Verificar que el usuario esté en sesión
	    // Obtener el proyecto con el ID proporcionado
	    Pagina paginaAEntrar = sp.encontrarPagina(paginaId);
	    model.addAttribute("paginaMostrar",paginaAEntrar);
	    
	    // Verificar si el proyecto pertenece al usuario en sesión
	    if(paginaAEntrar.getTipoPagina().equals("habitos")) {
	    	return "paginaHabitos.jsp";
	    } else if(paginaAEntrar.getTipoPagina().equals("bloc")) {
	    	return "paginaBloc.jsp";
	    } else {
	    	return "redirect:/dashboard"; 
	    }
	}
	
	@PostMapping("/bloc")
	public String crearDoc(@RequestParam("tipoTexto")String tipoTexto, @RequestParam("contenido")String contenido,@RequestParam("pagina")Long pagina) {
		
		sp.crearTarea(pagina, contenido, null, tipoTexto, null);
		
		return "redirect:/paginas/"+pagina;
	}


	@GetMapping("/proyectos/{proyectoId}/estudiante/habitos")
	public String mostrarPaginaHabitosEstudiante(@PathVariable Long proyectoId, Model model) {
	  Proyecto nuevoProyecto = sp.encontrarProyecto(proyectoId);
	  List<Pagina> listaPaginas = nuevoProyecto.getProyectoPaginas();
	  for(Pagina pagina:listaPaginas) {
		  if(pagina.getTipoPagina().equals("habitos")) {
			  model.addAttribute("paginaHabitos",pagina);
		  }
	  }
	    return "habitosestudiante.jsp";
	}
	
	@GetMapping("/proyectos/{proyectoId}/gimnasio/habitos")
	public String mostrarPaginaHabitosGimnasio(@PathVariable Long proyectoId, Model model) {
		  Proyecto nuevoProyecto = sp.encontrarProyecto(proyectoId);
		  List<Pagina> listaPaginas = nuevoProyecto.getProyectoPaginas();
		  for(Pagina pagina:listaPaginas) {
			  if(pagina.getTipoPagina().equals("habitos")) {
				  model.addAttribute("paginaHabitos",pagina);
			  }
		  }
		    return "habitosgimnasio	.jsp";
	}
}
