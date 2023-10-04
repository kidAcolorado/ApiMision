package com.viewnext.kidaprojects.apimision.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "misiones")
public class Mision {

	/**
	 * La clase {@code Mision} representa una misión en el sistema. Contiene información
	 * como el identificador de la misión, el nombre, la descripción, el nivel de dificultad,
	 * la recompensa, el estado de superación y el estado activa/inactiva.
	 *
	 * <p>
	 * El autor de esta clase es Víctor Colorado "Kid A".
	 * </p>
	 *
	 * @version 1.0
	 * @since 4 de Octubre de 2023
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMision;
	
	private String nombre;
	private String descripcion;
	private int nivel;
	private int recompensa;
	private boolean superada;
	private boolean activa;
	
	public Mision(String nombre, String descripcion, int nivel, int recompensa) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.recompensa = recompensa;
		this.superada = false;
		this.activa = true;
	}

	public Mision() {
		super();
	}

	public int getIdMision() {
		return idMision;
	}

	public void setIdMision(int idMision) {
		this.idMision = idMision;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(int recompensa) {
		this.recompensa = recompensa;
	}

	public boolean isSuperada() {
		return superada;
	}

	public void setSuperada(boolean superada) {
		this.superada = superada;
	}
	
	

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMision);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mision other = (Mision) obj;
		return idMision == other.idMision;
	}

	@Override
	public String toString() {
		return "Mision [idMision=" + idMision + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivel="
				+ nivel + ", recompensa=" + recompensa + ", superada=" + superada + ", activa=" + activa + "]";
	}

	
	
	
	
}
