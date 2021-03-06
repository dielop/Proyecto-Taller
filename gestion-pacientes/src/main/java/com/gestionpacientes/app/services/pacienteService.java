package com.gestionpacientes.app.services;

import javax.transaction.Transactional;

import com.gestionpacientes.app.model.Paciente;
import com.gestionpacientes.app.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class pacienteService {
	// Inyecto el repositorio, esto sirve para no iniciarlizarlo desde la clase si no de cuando sea necesario lo haga.
	@Autowired
	pacienteRepository pacienteRepository;
	
	// Busco todos los pacientes en la lista de pacientes
	public List<Paciente> List(){
		return pacienteRepository.findAll();
	}
	
	// Busco un paciente por ID
	public Optional<Paciente> getOne(int id){
		return pacienteRepository.findById(id);		
	}
	
	// Busco un paciente por nombre
	public Optional<Paciente> getByNombre(String nombre){
		return pacienteRepository.findByNombre(nombre);		
	}
	
	public void save(Paciente paciente) {
		pacienteRepository.save(paciente);
	}
	
	public void delete(int id) {
		pacienteRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return pacienteRepository.existsById(id);
	}
	
	public boolean existsByNombre(String nombre){
		return pacienteRepository.existsByNombre(nombre);
	}
	
	public boolean existsByDNI(int DNI){
		return pacienteRepository.existsByDNI(DNI);
	}
	
	public Optional<Paciente> getByDNI(int DNI){
		return pacienteRepository.findByDNI(DNI);		
	}
}
