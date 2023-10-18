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
								@Valid @ModelAttribute("nuevoProyec	to") Proyecto nuevoProyecto,
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
        } else {
        
        	// guardar proyecto nuevo
        	sp.crearProyectos(nuevoProyecto);
        	// Obtener la lista de proyectos actualizada
        	List<Proyecto> proyectos = sp.listaProyectoPorUsuario(usuarioTemporal);
        	// Agregar la lista actualizada de proyectos al modelo
        	model.addAttribute("proyectos", proyectos);
	       
        	String tipoPlantilla = nuevoProyecto.getPlantilla();

        	if ("estudiante".equals(tipoPlantilla)) {
        	    return "redirect:/proyectos/" + nuevoProyecto.getId() + "/estudiante/habitos";
        	} else if ("gimnasio".equals(tipoPlantilla)) {
        	    return "redirect:/proyectos/" + nuevoProyecto.getId() + "/gimnasio/habitos";
        	}
            return "redirect:/dashboard";  // Por ejemplo, redirigir de nuevo al dashboard.
        }
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
	
	@GetMapping("/proyectos/{proyectoId}/habitos")
	public String irAPrimeraPagina(@PathVariable Long proyectoId, HttpSession session) {
	    // Verificar que el usuario esté en sesión
	    Usuario usuarioTemporal = (Usuario) session.getAttribute("usuarioEnSesion");
	    if (usuarioTemporal == null) {
	        return "redirect:/";  
	    }
	    // Verificar que el usuario esté en sesión
	    // Obtener el proyecto con el ID proporcionado
	    Proyecto proyecto = sp.encontrarProyecto(proyectoId); 
	    
	    // Verificar si el proyecto pertenece al usuario en sesión
	    if (proyecto != null && proyecto.getCreador().equals(usuarioTemporal)) {
	        // Verificar la plantilla elegida
	        String tipoPlantilla = proyecto.getPlantilla();
	        if ("estudiante".equals(tipoPlantilla) || "gimnasio".equals(tipoPlantilla)) {
	            // Redirigir a la primera	 página de la plantilla de hábitos
	            return "redirect:/proyectos/" + proyectoId + "/" + tipoPlantilla + "/habitos";
	        }
	    }
	    
	    return "redirect:/dashboard"; 
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
