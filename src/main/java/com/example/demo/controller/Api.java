package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.Persona;
import com.example.demo.util.Registro;
import com.example.demo.util.Validaciones;

//Indico que es un servicio REST 
@RestController
@RequestMapping(path = "api/pueblo") // ruta
public class Api {

	private static final String DNI = "DNI";

	// Creamos un metodo de coger datos
	// Request param es el parametro de la url despues de ?, indico que el nombre de
	// este
	// reques es dni
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path = "/damePersona", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Persona> getData(@RequestParam(name = "dni") String pDni) {

		// lo que vamos a pedir
		Persona personita = new Persona();
		ResponseEntity<Persona> resp = null;

		Registro regis = new Registro();
		personita = regis.buscarCiudadanoCampo(pDni, DNI);

		if (personita.getDni() != null) {
			// @SuppressWarnings("rawtypes")
			resp = new ResponseEntity(personita, HttpStatus.OK);
		} else if (personita.getDni() == null) {

			resp = new ResponseEntity(HttpStatus.NOT_FOUND);

		} else {
			resp = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resp;

	}

	// Creamos un metodo de coger datos
	// Request param es el parametro de la url despues de ?, indico que el nombre de
	// este
	// reques es dni
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path = "/damePersonas", produces = MediaType.APPLICATION_JSON_VALUE)

	// La respuesta de aquí es un tipo respuesta en el cuerpo. La respuesta se va a
	// pintar en el cuerpo de la pagina
	public @ResponseBody ResponseEntity<Persona> getData() {

		// lo que vamos a pedir
		// Response Entity, tipo de dato para que lo lea la web
		List<Persona> personitas = new ArrayList<>();
		ResponseEntity<Persona> resp = null;

		Registro regis = new Registro();
		personitas = regis.listarCiudadanos();

		if (personitas.size() > 0) {
			// @SuppressWarnings("rawtypes")
			resp = new ResponseEntity(personitas, HttpStatus.OK);

		} else if (personitas.size() == 0) {

			resp = new ResponseEntity(HttpStatus.NOT_FOUND);

		} else {
			resp = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resp;

	}

	// Borra pesona y muestrame persona borrada
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping(path = "/borrarPersona/{pDni}", produces = MediaType.APPLICATION_JSON_VALUE)

	// La respuesta de aquí es un tipo respuesta en el cuerpo. La respuesta se va a
	// pintar en el cuerpo de la pagina
	public @ResponseBody ResponseEntity<Persona> deletData(@PathVariable String pDni) {

		ResponseEntity<Persona> resp;
		// lo que vamos a pedir
		// Response Entity, tipo de dato para que lo lea la web
		if (!Validaciones.validaDni(pDni)) {
			resp = new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
		} else {
			Registro regis = new Registro();
			Persona personaEliminada = regis.buscarCiudadanoCampo(pDni, DNI);

			if (personaEliminada.getDni() != null) {
				regis.eliminarCiudadano(pDni);

				resp = new ResponseEntity(personaEliminada, HttpStatus.OK);
			} else if (personaEliminada.getDni() == null) {
				resp = new ResponseEntity(HttpStatus.NOT_FOUND);
			} else {
				resp = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

		return resp;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(path = "/newPersona", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

	// La respuesta de aquí es un tipo respuesta en el cuerpo. La respuesta se va a
	// pintar en el cuerpo de la pagina
	public @ResponseBody ResponseEntity<Persona> createData(@RequestBody Persona persona) {
		Registro regis  = new Registro();

		int count = regis.insertarCiudadano(persona);
		ResponseEntity<Persona> resp;
		
		if (count != 0) {
			resp = new ResponseEntity(persona, HttpStatus.OK); 
		}else {
			resp =  new ResponseEntity(HttpStatus.CONFLICT);
		}
		
		return resp;

	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping(path = "/updatePersonas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

	// La respuesta de aquí es un tipo respuesta en el cuerpo. La respuesta se va a
	// pintar en el cuerpo de la pagina
	public @ResponseBody ResponseEntity<Persona> updateData(@RequestBody Persona persona) {
		Registro regis  = new Registro();

		int count = regis.updateCiudadano(persona);
		ResponseEntity<Persona> resp;
		
		if (count != 0) {
			resp = new ResponseEntity(persona, HttpStatus.CREATED); 
		}else {
			resp =  new ResponseEntity(HttpStatus.CONFLICT);
		}
		
		return resp;
	}

	/*
	{	
		"nombre" : "pepe",
		"apellido1" : "lopez",
		"apellido2" : "garcia",
		"dni" : "02125684A",
		"numTelefono" : "258456369"  
	} 
	 
	 */
	
	
	
	
	
	
}



