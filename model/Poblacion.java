package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
@Entity
@NamedQuery(name = "Poblacion.findAll", query = "SELECT p FROM Poblacion p")
public class Poblacion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)/*AUTOINCREMENT*/
	@Column(name = "id_poblacion")
	private int id_poblacion;
	
	@Column(name = "nom_poblacion")
	private String nomPoblacion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pais")
	private Pais id_pais;
	
	public Poblacion() {
		super();
	}

	public Poblacion(String nomPoblacion) {
		super();
		this.nomPoblacion = nomPoblacion;
	}

	public int getId_poblacion() {
		return id_poblacion;
	}

	public void setId_poblacion(int id_poblacion) {
		this.id_poblacion = id_poblacion;
	}

	public String getNomPoblacion() {
		return nomPoblacion;
	}

	public void setNomPoblacion(String nomPoblacion) {
		this.nomPoblacion = nomPoblacion;
	}

	public Pais getId_pais() {
		return id_pais;
	}

	public void setId_pais(Pais id_pais) {
		this.id_pais = id_pais;
	}



}
