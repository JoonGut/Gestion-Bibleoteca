package modelo_DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Autor;
import model.Editorial;
import model.Libro;

public class Libros_DAO implements Patron_DAO<Libro, String> {

	@Override
	public boolean insertar(Libro libro) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		// //////////////////////////////////////////
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(libro);
			entityTransaction.commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public boolean borrar(String pk) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			Libro libro = entityManager.find(Libro.class, pk);
			entityManager.remove(libro);
			entityTransaction.commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public boolean actualizar(Libro libro) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.merge(libro); /*MODIFICA*/
			entityTransaction.commit();
			 return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			entityManager.close();
			entityManagerFactory.close();
		}

	}

	@Override
	public Libro buscar(Object pk) {
	    EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            return em.find(Libro.class, pk);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            em.close();
	            emf.close();
	        }
	}

	@Override
	public ArrayList<Libro> listarTodos() {
	    EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            List<Libro> lista =
	                em.createNamedQuery("Libro.findAllWithRelations", Libro.class)
	                  .getResultList();

	            return new ArrayList<>(lista);

	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ArrayList<>();

	        } finally {
	            em.close();
	            emf.close();
	        }
	}
	public Libro buscarConRelaciones(String isbn) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion_bibleoteca");
	    EntityManager em = emf.createEntityManager();
	    try {
	        return em.createQuery(
	            "SELECT l FROM Libro l " +
	            "LEFT JOIN FETCH l.autor " +
	            "LEFT JOIN FETCH l.categoria " +
	            "LEFT JOIN FETCH l.editorial " +
	            "WHERE l.isbn = :isbn", Libro.class)
	            .setParameter("isbn", isbn)
	            .getSingleResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        em.close();
	        emf.close();
	    }
	}

}
