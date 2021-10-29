package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the tramite database table.
 * 
 */
@Entity
@Table(name="tramite")
@NamedQuery(name="Tramite.findAll", query="SELECT t FROM Tramite t")
public class Tramite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tramite")
	private int idTramite;

	private String descripcion;

	private String titulo;

	//bi-directional many-to-one association to Paso
	@OneToMany(mappedBy="tramite")
	private List<Paso> pasos;

	//bi-directional many-to-one association to Alcaldia
	@ManyToOne
	@JoinColumn(name="alcaldia_id")
	@JsonIgnore
	private Alcaldia alcaldia;

	public Tramite() {
	}

	public int getIdTramite() {
		return this.idTramite;
	}

	public void setIdTramite(int idTramite) {
		this.idTramite = idTramite;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Paso> getPasos() {
		return this.pasos;
	}

	public void setPasos(List<Paso> pasos) {
		this.pasos = pasos;
	}

	public Paso addPaso(Paso paso) {
		getPasos().add(paso);
		paso.setTramite(this);

		return paso;
	}

	public Paso removePaso(Paso paso) {
		getPasos().remove(paso);
		paso.setTramite(null);

		return paso;
	}

	public Alcaldia getAlcaldia() {
		return this.alcaldia;
	}

	public void setAlcaldia(Alcaldia alcaldia) {
		this.alcaldia = alcaldia;
	}

}