package com.gestionpacientes.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionpacientes.app.model.Paciente;

@Repository
public interface pacienteRepository extends JpaRepository<Paciente , Integer> {
	Optional<Paciente> findByNombre(String nombre);
	Optional<Paciente> findByDNI(int DNI);
	Boolean existsByNombre(String nombre);
	Boolean existsByDNI(int DNI);
}
