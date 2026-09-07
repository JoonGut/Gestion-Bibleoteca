package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the autor database table.
 * 
 */
@Entity
@Table(name = "prueba")
public class Prueba implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codPrueba;

	private String nomPrueba;
	
	private String nomPrueba2;

	public Prueba() {
	}

	public int getCodPrueba() {
		return codPrueba;
	}

	public void setCodPrueba(int codPrueba) {
		this.codPrueba = codPrueba;
	}

	public String getNomPrueba() {
		return nomPrueba;
	}

	public void setNomPrueba(String nomPrueba) {
		this.nomPrueba = nomPrueba;
	}
	
	

	public String getNomPrueba2() {
		return nomPrueba2;
	}

	public void setNomPrueba2(String nomPrueba2) {
		this.nomPrueba2 = nomPrueba2;
	}

	@Override
	public String toString() {
		return "Prueba [codPrueba=" + codPrueba + ", nomPrueba=" + nomPrueba + ", nomPrueba2=" + nomPrueba2 + "]";
	}

	

}