package com.viewnext.kidaprojects.apimision.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewnext.kidaprojects.apimision.model.Mision;
import com.viewnext.kidaprojects.apimision.repository.MisionRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * La clase {@code MisionServiceImpl} implementa la interfaz
 * {@code MisionService} y proporciona la lógica de negocio para gestionar
 * misiones en el sistema.
 *
 * <p>
 * El autor de esta clase es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 4 de Octubre de 2023
 */
@Service
public class MisionServiceImpl implements MisionService {

	@Autowired
	private MisionRepository misionRepository;

	/**
	 * Recupera todas las misiones disponibles en el sistema.
	 *
	 * @return Una lista de todas las misiones.
	 * @throws EntityNotFoundException Si no se encuentran misiones en el sistema.
	 */
	@Override
	public List<Mision> showAll() throws EntityNotFoundException {
		List<Mision> listaMisiones = misionRepository.findAll();

		if (listaMisiones.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return listaMisiones;
	}

	/**
	 * Recupera misiones cuyos nombres coinciden con un prefijo dado.
	 *
	 * @param prefijo El prefijo del nombre de la misión a buscar.
	 * @return Una lista de misiones cuyos nombres coinciden con el prefijo.
	 * @throws EntityNotFoundException Si no se encuentran misiones con el nombre
	 *                                 que coincida con el prefijo.
	 */
	@Override
	public List<Mision> showByNombreLike(String prefijo) throws EntityNotFoundException {
		List<Mision> listaMisiones = misionRepository.findByNombrePrefijo(prefijo);

		if (listaMisiones.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return listaMisiones;
	}

	/**
	 * Recupera misiones cuyo nivel de dificultad sea igual o menor al nivel
	 * especificado.
	 *
	 * @param nivel El nivel de dificultad máximo deseado.
	 * @return Una lista de misiones con niveles de dificultad iguales o menores al
	 *         nivel especificado.
	 * @throws EntityNotFoundException Si no se encuentran misiones con el nivel de
	 *                                 dificultad especificado o menor.
	 */
	@Override
	public List<Mision> showByNivel(int nivel) throws EntityNotFoundException {
		List<Mision> listaMisiones = misionRepository.findByNivel(nivel);

		if (listaMisiones.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return listaMisiones;
	}

	/**
	 * Recupera una misión por su identificador único.
	 *
	 * @param idMision El identificador único de la misión a recuperar.
	 * @return La misión correspondiente al identificador dado.
	 * @throws EntityNotFoundException Si no se encuentra una misión con el
	 *                                 identificador especificado.
	 */
	@Override
	public Mision showById(int idMision) throws EntityNotFoundException {
		Optional<Mision> optionalMision = misionRepository.findById(idMision);

		if (optionalMision.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return optionalMision.get();
	}

	/**
	 * Obtiene la recompensa asociada a una misión específica identificada por su
	 * ID.
	 *
	 * @param idMision El ID de la misión de la cual se desea obtener la recompensa.
	 * @return La cantidad de recompensa asociada a la misión especificada.
	 * @throws EntityNotFoundException Si no se encuentra una misión con el ID
	 *                                 especificado.
	 */
	@Override
	public int getRecompensa(int idMision) throws EntityNotFoundException {
		Optional<Mision> optionalMision = misionRepository.findById(idMision);

		if (optionalMision.isEmpty()) {
			throw new EntityNotFoundException();
		}

		Mision mision = optionalMision.get();

		return mision.getRecompensa();
	}

	/**
	 * Crea una nueva misión en el sistema.
	 *
	 * @param mision La misión que se va a crear.
	 * @return La misión creada con su identificador único.
	 */
	@Override
	public Mision createMision(Mision mision) {

		return misionRepository.save(mision);
	}

	/**
	 * Establece el estado de una misión como "Superada" mediante su código único.
	 *
	 * @param codigo El código único de la misión a marcar como "Superada".
	 * @return La misión con el estado actualizado.
	 * @throws EntityNotFoundException Si no se encuentra una misión con el código
	 *                                 especificado.
	 */
	@Override
	public Mision setEstadoSuperada(int codigo) throws EntityNotFoundException {
		Optional<Mision> optionalMision = misionRepository.findById(codigo);

		if (optionalMision.isEmpty()) {
			throw new EntityNotFoundException();
		}

		Mision mision = optionalMision.get();

		mision.setSuperada(true);

		return misionRepository.save(mision);
	}

	/**
	 * Establece el estado de una misión como "Inactiva" mediante su código único.
	 *
	 * @param codigo El código único de la misión a marcar como "Inactiva".
	 * @return La misión con el estado actualizado.
	 * @throws EntityNotFoundException Si no se encuentra una misión con el código
	 *                                 especificado.
	 */
	@Override
	public Mision setEstadoInactiva(int codigo) throws EntityNotFoundException {
		Optional<Mision> optionalMision = misionRepository.findById(codigo);

		if (optionalMision.isEmpty()) {
			throw new EntityNotFoundException();
		}

		Mision mision = optionalMision.get();

		mision.setActiva(false);

		return misionRepository.save(mision);
	}

	/**
	 * Reinicia el estado de todas las misiones en el sistema, estableciendo su
	 * estado como activas y no superadas. Este método se utiliza para reiniciar
	 * todas las misiones en el sistema.
	 */
	@Override
	public void reiniciarMisiones() {
		List<Mision> listaMisiones = misionRepository.findAll();

		for (Mision m : listaMisiones) {
			m.setActiva(true);
			m.setSuperada(false);
		}

		misionRepository.saveAll(listaMisiones);

	}

}
