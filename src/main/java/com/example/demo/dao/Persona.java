package com.example.demo.dao;

import java.io.Serializable;
import java.sql.Date;

public class Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String nombre, apellido1, apellido2, dni, numTelefono;
	private Date fechaNacimiento;
	
		
	//Getters y setters de los Datos
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido) {
		this.apellido1 = apellido;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNumTelefono() {
		return numTelefono;
	}
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	@Override
	public String toString() {
		return "Persona [nombreCompleto=" + nombre + " " + apellido1 + " " + apellido2 + ", dni=" + dni
				+ ", numTelefono=" + numTelefono + ", fechaNacimiento=" + fechaNacimiento +  "]";
	}
	
	
}
