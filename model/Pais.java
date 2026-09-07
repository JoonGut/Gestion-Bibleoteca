package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)/*AUTOINCREMENT*/
	@Column(name = "id_pais")
	private int id_pais;

	@Column(name = "nom_pais")
	private String nomPais;
	/*CONSTRUCTORES*/
	public Pais() {
		super();
	}
	
	
	public Pais(String nomPais) {
		super();
		this.nomPais = nomPais;
	}


	/*GETTERS Y SETTERS*/

	public int getId_pais() {
		return id_pais;
	}

	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}

	public String getNomPais() {
		return nomPais;
	}

	public void setNomPais(String nomPais) {
		this.nomPais = nomPais;
	}
	/*TOSTRING*/


	@Override
	public String toString() {
		return  nomPais;
	}
	
	
	
}
