package com.gestionpacientes.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
// No hace falta esto porque la tabla de la entidad se llama como mi clase @Table(name = "Paciente")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int DNI;
	private String nombre;
	private String apellido;
	private String localidad;
	private String direccion;
	private int direccionNro;
	private int telefono;
	
	public Paciente() {
		
	}
	
	public Paciente(int DNI, String nombre, String apellido, String localidad, String direccion, int direccionNro, int telefono) {
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.localidad = localidad;
		this.direccion = direccion;
		this.direccionNro = direccionNro;
		this.telefono = telefono;
	}
	
	// Getters and setters
	public int getDNI() {
		return DNI;
	}
	
	public void setDNI(int DNI) {
		this.DNI = DNI;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		 this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getDireccionNro() {
		return direccionNro;
	}

	public void setDireccionNro(int direccionNro) {
		this.direccionNro = direccionNro;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	

}
