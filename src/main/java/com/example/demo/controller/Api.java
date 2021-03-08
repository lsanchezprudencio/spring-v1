package com.example.demo.controller;




import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Persona;
import com.example.demo.util.Registro;




//Indico que es un servicio REST 
@RestController 
@RequestMapping (path = "api/pueblo")// ruta 
public class Api {
	
	//Creamos un metodo de coger datos
	//Request param es el parametro de la url despues de ?, indico que el nombre de este
	//reques es dni
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping (path ="/damePersona", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  ResponseEntity<Persona> getData(@RequestParam(name= "dni")String pDni) {
			
		//lo que vamos a pedir
		Persona personita = new Persona();
		ResponseEntity <Persona> resp = null;
		
		
		Registro regis =new Registro();
		personita = regis.buscarCiudadanoCampo(pDni, "DNI");
		
		if(personita.getDni() != null) {
			//@SuppressWarnings("rawtypes")
			resp = new ResponseEntity(personita, HttpStatus.OK) ;
		}else if (personita.getDni()==null){

			resp = new ResponseEntity(HttpStatus.NOT_FOUND) ;

		} else {
			resp = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		return resp;
		
		
	}
	
	
	//Creamos un metodo de coger datos
		//Request param es el parametro de la url despues de ?, indico que el nombre de este
		//reques es dni
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@GetMapping (path ="/damePersonas", produces = MediaType.APPLICATION_JSON_VALUE)
	
		//La respuesta de aquí es un tipo respuesta en el cuerpo. La respuesta se va a pintar en el cuerpo de la pagina
		public @ResponseBody  ResponseEntity<Persona> getData() {
				
			//lo que vamos a pedir
			//Response Entity, tipo de dato para que lo lea la web
			List<Persona> personitas= new ArrayList<>();
			ResponseEntity <Persona> resp = null;
			
			
			Registro regis =new Registro();
			personitas = regis.listarCiudadanos();
			
			if(personitas.size()>0) {
				//@SuppressWarnings("rawtypes")
				resp = new ResponseEntity(personitas, HttpStatus.OK) ;
				
				
			}else if (personitas.size()==0){

				resp = new ResponseEntity(HttpStatus.NOT_FOUND) ;

			} else {
				resp = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			
			return resp;
			
			
		}
		
		//Borra pesona y muestrame los que quedan en la bbdd
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping (path ="/borraPersona", produces = MediaType.APPLICATION_JSON_VALUE)
			
				//La respuesta de aquí es un tipo respuesta en el cuerpo. La respuesta se va a pintar en el cuerpo de la pagina
				public @ResponseBody  ResponseEntity<Persona> deletData(@RequestParam (name = "dni") String pDni) {
						
					//lo que vamos a pedir
					//Response Entity, tipo de dato para que lo lea la web
					
					
					List<Persona> personitas= new ArrayList<>();
					ResponseEntity <Persona> resp = null;
					
					
					Registro regis =new Registro();
					int ciudadanoEliminado = regis.eliminarCiudadano(pDni);
					System.out.println(ciudadanoEliminado);
					
					personitas = regis.listarCiudadanos();
					
					if(personitas.size()>0) {
						//@SuppressWarnings("rawtypes")
						resp = new ResponseEntity(personitas, HttpStatus.OK) ;
						
						
					}else if (personitas.size()==0){

						resp = new ResponseEntity(HttpStatus.NOT_FOUND) ;

					} else {
						resp = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
					}

					
					return resp;
					
					
				}
		
				//Borra pesona y muestrame persona borrada
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping (path ="/borraPersona2", produces = MediaType.APPLICATION_JSON_VALUE)
			
				//La respuesta de aquí es un tipo respuesta en el cuerpo. La respuesta se va a pintar en el cuerpo de la pagina
				public @ResponseBody  ResponseEntity<Persona> deletData2(@RequestParam (name = "dni") String pDni) {
						
					//lo que vamos a pedir
					//Response Entity, tipo de dato para que lo lea la web
					
					Registro regis = new Registro();
					Persona personaEliminada=regis.buscarCiudadanoCampo(pDni, "DNI");
					ResponseEntity<Persona> resp;
					
					if(personaEliminada.getDni() != null) {
						regis.eliminarCiudadano(pDni);
						resp = new ResponseEntity(personaEliminada, HttpStatus.OK);
					}else if(personaEliminada.getDni()== null) {
						resp = new ResponseEntity(HttpStatus.NOT_FOUND);
					}else {
						resp = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
					return resp;
					
					
				}

	
}
