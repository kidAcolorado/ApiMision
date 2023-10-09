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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.viewnext.kidaprojects.apimision.model.Mision;
import com.viewnext.kidaprojects.apimision.service.MisionService;
import jakarta.persistence.EntityNotFoundException;

@RestController
public class MisionRestController {

	@Autowired
	private MisionService service;
	
	private static final String MISION_NOT_FOUND = "Misión/es no encontrada";
	
	/**
     * Obtiene todas las misiones disponibles.
     *
     * @return Una lista de todas las misiones.
     */
	@GetMapping(value = "mision", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showAllMisiones(){
		try {
			List<Mision> listaMisiones = service.showAll();
			return ResponseEntity.ok(listaMisiones);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
	}
	
	/**
     * Obtiene una misión por su ID.
     *
     * @param idMision El ID de la misión que se desea obtener.
     * @return La misión con el ID especificado.
     */
	@GetMapping(value = "mision/{idMision}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showMisionById(@PathVariable("idMision") int idMision){
		try {
			Mision mision = service.showById(idMision);
			return ResponseEntity.ok(mision);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
	}
	
	/**
     * Obtiene la recompensa de una misión por su ID.
     *
     * @param idMision El ID de la misión de la cual se desea obtener la recompensa.
     * @return La recompensa de la misión especificada.
     */
	@GetMapping(value = "mision/recompensa/{idMision}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRecompensaByIdMision(@PathVariable("idMision") int idMision){
		try {
			int recompensa = service.getRecompensa(idMision);
			return ResponseEntity.ok(recompensa);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		
		}
	}
	
	/**
     * Obtiene misiones cuyo nombre contiene un prefijo dado.
     *
     * @param prefijo El prefijo que debe coincidir con el nombre de las misiones.
     * @return Una lista de misiones cuyos nombres coinciden con el prefijo especificado.
     */
	@GetMapping(value = "mision/nombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showMisionesByNombreLike(@RequestParam("prefijo") String prefijo){
		try {
			List<Mision> listaMisiones = service.showByNombreLike(prefijo);
			return ResponseEntity.ok(listaMisiones);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
		
	}
	
	/**
     * Obtiene misiones por su nivel.
     *
     * @param nivel El nivel de las misiones que se desean obtener.
     * @return Una lista de misiones con el nivel especificado.
     */
	@GetMapping(value = "mision/nivel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showMisionesByNivel(@RequestParam("nivel") int nivel){
		try {
			List<Mision> listaMisiones = service.showByNivel(nivel);
			return ResponseEntity.ok(listaMisiones);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
	}
	
	
	/**
     * Crea una nueva misión.
     *
     * @param mision La misión que se desea crear.
     * @return La misión creada y su ubicación en el recurso.
     */
	@PostMapping(value = "mision", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mision> createMision(@RequestBody Mision mision){
		 Mision misionCreada = service.createMision(mision);

		    // Utiliza la ruta relativa al recurso
		    URI location = URI.create("/mision/" + misionCreada.getIdMision());

		    // Devuelve una respuesta con el código 201 Created y la URI del nuevo recurso
		    return ResponseEntity.created(location).body(misionCreada);
	}
	
	/**
     * Actualiza el estado de una misión a "Superada" por su ID.
     *
     * @param idMision El ID de la misión que se desea actualizar.
     * @return La misión actualizada con el estado "Superada".
     */
	@PutMapping(value = "mision/{idMision}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEstadoSuperada(@PathVariable("idMision") int idMision){
		try {
			Mision misionActualizada = service.setEstadoSuperada(idMision);
			return ResponseEntity.ok(misionActualizada);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
	}
	
	/**
     * Establece el estado de una misión a "Inactiva" por su ID.
     *
     * @param idMision El ID de la misión que se desea establecer como "Inactiva".
     * @return La misión con el estado "Inactiva".
     */
	@DeleteMapping(value = "mision/{idMision}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> setEstadoInactiva(@PathVariable("idMision") int idMision){
		try {
			Mision misionInactiva = service.setEstadoInactiva(idMision);
			return ResponseEntity.ok(misionInactiva);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MISION_NOT_FOUND);
		}
		
	}
	
	/**
     * Reinicia todas las misiones.
     *
     * @return Un mensaje indicando que las misiones han sido reiniciadas.
     */
	@PostMapping(value = "mision/reinicio")
	public ResponseEntity<String> reiniciarMisiones(){
		service.reiniciarMisiones();
		return ResponseEntity.ok().body("Misiones reiniciadas");
	}
	
	
	
}
