package com.codingdojo.sebastian.servicios;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.sebastian.modelos.Usuario;
import com.codingdojo.sebastian.repositorios.RepositorioUsuarios;

@Service
public class Servicio {
	
	@Autowired
	private RepositorioUsuarios repoUsuario;
	
	public Usuario registrar(Usuario nuevoUsuario,BindingResult result) {
		
		String contrasena = nuevoUsuario.getPassword();
		String confirmacion = nuevoUsuario.getConfirmPassword();
		if(!contrasena.equals(confirmacion)) {
			result.rejectValue("confirmacion","matches", "las contraseñas no coinciden");
		}
		
		String email = nuevoUsuario.getEmail();
		Usuario existeUsuario = repoUsuario.findByEmail(email);
		if(existeUsuario != null) {
			result.rejectValue("email","Unique","el correo ingresado ya se encuentra registrado");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			String contra_encriptada = BCrypt.hashpw(contrasena, BCrypt.gensalt());
			nuevoUsuario.setPassword(contra_encriptada);
			return repoUsuario.save(nuevoUsuario);
		}
			
	}
	
	
	
	public Usuario login(String email, String password) {
		
		Usuario usuarioInicioSesion = repoUsuario.findByEmail(email);
		if(usuarioInicioSesion==null) {
			return null;
		}
		
		if(BCrypt.checkpw(password, usuarioInicioSesion.getPassword())) {
			return usuarioInicioSesion;
		} 
		
		return null;
		
		
	}
	
}
