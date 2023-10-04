package com.viewnext.kidaprojects.apimision.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.viewnext.kidaprojects.apimision.model.Mision;

/**
 * La interfaz {@code MisionRepository} define métodos de acceso a la base de datos para la entidad {@code Mision}.
 * Permite realizar operaciones de búsqueda y persistencia de misiones.
 *
 * <p>
 * El autor de esta interfaz es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 4 de Octubre de 2023
 */
public interface MisionRepository extends JpaRepository<Mision, Integer> {

    /**
     * Busca misiones cuyos nombres comiencen con el prefijo especificado.
     *
     * @param prefijo El prefijo con el que deben comenzar los nombres de las misiones.
     * @return Una lista de misiones que cumplen con el criterio de búsqueda.
     */
    @Query("SELECT m FROM Mision m WHERE m.nombre LIKE :prefijo%")
    List<Mision> findByNombrePrefijo(@Param("prefijo") String prefijo);

    /**
     * Busca misiones cuyo nivel de dificultad sea igual o menor que el nivel especificado.
     *
     * @param nivel El nivel de dificultad máximo de las misiones a buscar.
     * @return Una lista de misiones que cumplen con el criterio de búsqueda.
     */
    @Query("SELECT m FROM Mision m WHERE m.nivel <= :nivel")
    List<Mision> findByNivel(@Param("nivel") int nivel);
}

