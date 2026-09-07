package modelo_DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Autor;
import model.Editorial;

public class Editorial_DAO implements Patron_DAO<Editorial, Integer> {

	@Override
	public boolean insertar(Editorial editorial) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(editorial);
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
			Editorial editorial = entityManager.find(Editorial.class, pk);
			entityManager.remove(editorial);
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
	public boolean actualizar(Editorial editorial) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.merge(editorial); /*MODIFICA*/
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
	public Editorial buscar(Object pk) {
	    EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            return em.find(Editorial.class, pk);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            em.close();
	            emf.close();
	        }
	}

	@Override
	public ArrayList<Editorial> listarTodos() {
	    EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            List<Editorial> lista =
	                em.createNamedQuery("Editorial.findAll", Editorial.class)
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
