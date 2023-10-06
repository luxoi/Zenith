package com.codingdojo.sebastian.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.sebastian.modelos.Proyecto;
import com.codingdojo.sebastian.servicios.ServicioProyectos;

import jakarta.validation.Valid;

@Controller
public class ControladorPrueba {

	@Autowired
	private ServicioProyectos servicio;
	
	
	@GetMapping("/prueba")
	public String prueba(@ModelAttribute("prueba")Proyecto nuevoProyecto) {
		
		return "Prueba.jsp";
	}
	
	@PostMapping("/pruebaCrear")
	public String pruebaC(@Valid @ModelAttribute("prueba")Proyecto nuevoProyecto,BindingResult result) {
		if(result.hasErrors()) {
			return "Prueba.jsp";
		} else {
			Proyecto proyectoPlantilla = servicio.crearProyectos(nuevoProyecto);
			servicio.guardarProyecto(proyectoPlantilla);
			return "redirect:/";
		}
	}
}
