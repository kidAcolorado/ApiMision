package com.viewnext.kidaprojects.apimision.restcontroller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.viewnext.kidaprojects.apimision.model.Mision;
import com.viewnext.kidaprojects.apimision.service.MisionService;
import jakarta.persistence.EntityNotFoundException;

@RestController
public class MisionRestController {

	@Autowired
	private MisionService service;
	
	private static final String MISION_NOT_FOUND = "Misión/es no encontrada";
	
	@GetMapping(value = "mision", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showAllMisiones(){
		try {
			List<Mision> listaMisiones = service.showAll();
			return ResponseEntity.ok(listaMisiones);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
	}
	
	@GetMapping(value = "mision/{idMision}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showMisionById(@PathVariable("idMision") int idMision){
		try {
			Mision mision = service.showById(idMision);
			return ResponseEntity.ok(mision);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
	}
	
	@GetMapping(value = "mision/nombre/{prefijo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showMisionesByPrefijoLike(@PathVariable("prefijo") String prefijo){
		try {
			List<Mision> listaMisiones = service.showByNombreLike(prefijo);
			return ResponseEntity.ok(listaMisiones);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
		
	}
	
	@GetMapping(value = "mision/nivel/{nivel}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showMisionesByNivel(@PathVariable("nivel") int nivel){
		try {
			List<Mision> listaMisiones = service.showByNivel(nivel);
			return ResponseEntity.ok(listaMisiones);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
	}
	
	@PostMapping(value = "mision", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mision> createMision(@RequestBody Mision mision){
		 Mision misionCreada = service.createMision(mision);

		    // Utiliza la ruta relativa al recurso
		    URI location = URI.create("/mision/" + misionCreada.getIdMision());

		    // Devuelve una respuesta con el código 201 Created y la URI del nuevo recurso
		    return ResponseEntity.created(location).body(misionCreada);
	}
	
	@PutMapping(value = "mision/{idMision}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEstadoSuperada(@PathVariable("idMision") int idMision){
		try {
			Mision misionActualizada = service.setEstadoSuperada(idMision);
			return ResponseEntity.ok(misionActualizada);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "mision/{idMision}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> setEstadoInactiva(@PathVariable("idMision") int idMision){
		try {
			Mision misionInactiva = service.setEstadoInactiva(idMision);
			return ResponseEntity.ok(misionInactiva);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "mision/reinicio")
	public ResponseEntity<String> reiniciarMisiones(){
		service.reiniciarMisiones();
		return ResponseEntity.ok().body("Misiones reiniciadas");
	}
	
	
	
}
