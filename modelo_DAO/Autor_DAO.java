package modelo_DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Autor;

public class Autor_DAO implements Patron_DAO<Autor, Integer> {

	@Override
	public boolean insertar(Autor autor) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		// //////////////////////////////////////////
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(autor);
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
	public boolean borrar(Integer pk) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			Autor autor = entityManager.find(Autor.class, pk);
			entityManager.remove(autor);
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
	public boolean actualizar(Autor autor) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.merge(autor); /*MODIFICA*/
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
	public Autor buscar(Object pk) {
	    EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            return em.find(Autor.class, pk);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            em.close();
	            emf.close();
	        }
	}

	@Override
	public ArrayList<Autor> listarTodos() {
	    EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            List<Autor> lista =
	                em.createNamedQuery("Autor.findAll", Autor.class)
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

}
