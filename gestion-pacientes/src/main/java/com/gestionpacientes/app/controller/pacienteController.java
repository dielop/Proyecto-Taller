package com.gestionpacientes.app.controller;



import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionpacientes.app.dto.pacienteDto;
import com.gestionpacientes.app.dto.pacienteMensaje;
import com.gestionpacientes.app.model.Paciente;
import com.gestionpacientes.app.services.pacienteService;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "http://localhost:4200")

public class pacienteController {
	
	@Autowired
	pacienteService pacienteService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Paciente>> list(){
		List<Paciente> list = pacienteService.List();
		return new ResponseEntity<List<Paciente>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Paciente> getById(@PathVariable("id") int id){
		//Verifico si existe el id, si no existe retorno el mensaje
		if(!pacienteService.existsById(id)) {
			return new ResponseEntity(new pacienteMensaje("No existe el paciente con el ID solicitado"), HttpStatus.NOT_FOUND);
		}else{
		// Como es optional tengo que usar el get, variable paciente de tipo Paciente
		Paciente paciente = pacienteService.getOne(id).get();
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
		}
	}
	
	@GetMapping("/detailname/{nombre}")
	public ResponseEntity<Paciente> getByNombre(@PathVariable("nombre") String nombre){
		//Verifico si existe el id, si no existe retorno el mensaje
		if(!pacienteService.existsByNombre(nombre)) {
			return new ResponseEntity(new pacienteMensaje("No existe el paciente - Nombre inexistente"), HttpStatus.NOT_FOUND);
		}else{
		// Como es optional tengo que usar el get, variable paciente de tipo Paciente
		Paciente paciente = pacienteService.getByNombre(nombre).get();
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
		}		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody pacienteDto pacienteDto){
		if(StringUtils.isBlank(pacienteDto.getNombre())) {
			return new ResponseEntity(new pacienteMensaje("Debe ingresar un nombre"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(pacienteDto.getApellido())) {
			return new ResponseEntity(new pacienteMensaje("Debe ingresar un apellido"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(Integer.toString(pacienteDto.getDNI()))) {
			return new ResponseEntity(new pacienteMensaje("Debe ingresar un DNI"), HttpStatus.BAD_REQUEST);
		}
		
		// Verifico si existe paciente con el DNI ingresado, si no, lo creamos 
		if(pacienteService.existsByDNI(pacienteDto.getDNI())){
			return new ResponseEntity(new pacienteMensaje("Ya existe un paciente con el DNI ingresado"), HttpStatus.BAD_REQUEST);
		}
		
		Paciente paciente = new Paciente(pacienteDto.getDNI(), pacienteDto.getNombre(), pacienteDto.getApellido(), pacienteDto.getLocalidad(), pacienteDto.getDireccion(), pacienteDto.getDireccionNro(), pacienteDto.getTelefono());
		pacienteService.save(paciente);
		
		return new ResponseEntity(new pacienteMensaje("Paciente creado con exito"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody pacienteDto pacienteDto){
		//Verifico si existe el id, si no existe retorno el mensaje
		if(!pacienteService.existsById(id)) {
			return new ResponseEntity(new pacienteMensaje("No existe paciente con el ID solicitado"), HttpStatus.NOT_FOUND);
		}
		
		// Si no existe el DNI y el DNI ingresado no matchea con ningun id entonces no existe
		if(!pacienteService.existsByDNI(pacienteDto.getDNI()) && pacienteService.getByDNI(pacienteDto.getDNI()).get().getId() != id) {
			return new ResponseEntity(new pacienteMensaje("No existe paciente con el DNI solicitado"), HttpStatus.NOT_FOUND);
		}

		// Verificacion de campos en blanco
		if(StringUtils.isBlank(pacienteDto.getNombre())) {
			return new ResponseEntity(new pacienteMensaje("Debe ingresar un nombre"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(pacienteDto.getApellido())) {
			return new ResponseEntity(new pacienteMensaje("Debe ingresar un apellido"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(Integer.toString(pacienteDto.getDNI()))) {
			return new ResponseEntity(new pacienteMensaje("Debe ingresar un DNI"), HttpStatus.BAD_REQUEST);
		}
		
		Paciente paciente = pacienteService.getOne(id).get();
		paciente.setDNI(pacienteDto.getDNI());
		paciente.setNombre(pacienteDto.getNombre());
		paciente.setApellido(pacienteDto.getApellido());
		paciente.setLocalidad(pacienteDto.getLocalidad());
		paciente.setDireccion(pacienteDto.getDireccion());
		paciente.setDireccionNro(pacienteDto.getDireccionNro());
		paciente.setTelefono(pacienteDto.getTelefono());
		
		pacienteService.save(paciente);
		
		return new ResponseEntity(new pacienteMensaje("Paciente actualizado con exito"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")int id){
		if(!pacienteService.existsById(id)) {
			return new ResponseEntity(new pacienteMensaje("No existe paciente con el ID solicitado"), HttpStatus.NOT_FOUND);
		}else {
			pacienteService.delete(id);
			return new ResponseEntity(new pacienteMensaje("Paciente eliminado con exito"), HttpStatus.OK);
		}
	}
		
}
