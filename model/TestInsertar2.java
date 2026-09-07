package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestInsertar2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		// //////////////////////////////////////////
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		// //////////////////////////////////////////////
		Autor autor = new Autor();
		autor.setNomAutor("Agatha Christie");
		entityManager.persist(autor);
		//
		Editorial editorial = new Editorial();
		editorial.setNomEditorial("Zugarto Ediciones");
		entityManager.persist(editorial);
		//
		Categoria categoria = new Categoria();
		categoria.setNomCategoria("Ciencia ficcion");
		entityManager.persist(categoria);
		//
		Libro libro = new Libro();
		libro.setIsbn("1266");
		libro.setPrecio(23.5);
		libro.setStock(22);
		libro.setTitulo("Harry Potter y la piedra filosofal");
		libro.setAutor(autor);
		libro.setEditorial(editorial);
		libro.setCategoria(categoria);
		entityManager.persist(libro);
		// ////////////////////////////////////////////
		entityTransaction.commit();
		entityManager.close();
		// //////////////////////////////////////////
		entityManagerFactory.close();
	}

}
