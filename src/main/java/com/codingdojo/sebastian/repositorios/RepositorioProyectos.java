package com.codingdojo.sebastian.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.sebastian.modelos.Proyecto;
import com.codingdojo.sebastian.modelos.Usuario;

public interface RepositorioProyectos extends CrudRepository<Proyecto, Long>{

	
	List <Proyecto> findAll();
	
}
