package com.viewnext.kidaprojects.apimision.service;

import java.util.List;

import com.viewnext.kidaprojects.apimision.model.Mision;

/**
 * La interfaz {@code MisionService} define las operaciones disponibles para
 * gestionar misiones en el sistema. Proporciona métodos para buscar, crear y
 * actualizar misiones, así como establecer su estado.
 *
 * <p>
 * El autor de esta interfaz es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 4 de Octubre de 2023
 */
public interface MisionService {

	/**
	 * Recupera todas las misiones disponibles en el sistema.
	 *
	 * @return Una lista de todas las misiones.
	 */
	List<Mision> showAll();

	/**
	 * Recupera misiones cuyos nombres coinciden con un prefijo dado.
	 *
	 * @param prefijo El prefijo del nombre de la misión a buscar.
	 * @return Una lista de misiones cuyos nombres coinciden con el prefijo.
	 */
	List<Mision> showByNombreLike(String prefijo);

	/**
	 * Recupera misiones cuyo nivel de dificultad sea igual o menor al nivel
	 * especificado.
	 *
	 * @param nivel El nivel de dificultad máximo deseado.
	 * @return Una lista de misiones con niveles de dificultad iguales o menores al
	 *         nivel especificado.
	 */
	List<Mision> showByNivel(int nivel);

	/**
	 * Recupera una misión por su identificador único.
	 *
	 * @param idMision El identificador único de la misión a recuperar.
	 * @return La misión correspondiente al identificador dado.
	 */
	Mision showById(int idMision);
	
	/**
	 * Obtiene la recompensa asociada a una misión específica identificada por su ID.
	 *
	 * @param idMision El ID de la misión de la cual se desea obtener la recompensa.
	 * @return La cantidad de recompensa asociada a la misión especificada, o -1 si la misión no existe.
	 */
	int getRecompensa(int idMision);

	/**
	 * Crea una nueva misión en el sistema.
	 *
	 * @param mision La misión que se va a crear.
	 * @return La misión creada con su identificador único.
	 */
	Mision createMision(Mision mision);

	/**
	 * Establece el estado de una misión como "Superada" mediante su código único.
	 *
	 * @param codigo El código único de la misión a marcar como "Superada".
	 * @return La misión con el estado actualizado.
	 */
	Mision setEstadoSuperada(int codigo);

	/**
	 * Establece el estado de una misión como "Inactiva" mediante su código único.
	 *
	 * @param codigo El código único de la misión a marcar como "Inactiva".
	 * @return La misión con el estado actualizado.
	 */
	Mision setEstadoInactiva(int codigo);

	/**
	 * Reinicia el estado de todas las misiones, estableciendo su estado como no
	 * superadas y activas. Este método se utiliza para reiniciar todas las misiones en el
	 * sistema.
	 */
	void reiniciarMisiones();
}
