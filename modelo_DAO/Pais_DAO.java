package modelo_DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Editorial;
import model.Libro;
import model.Pais;

public class Pais_DAO implements Patron_DAO<Pais, Integer> {

	@Override
	public boolean insertar(Pais pais) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(pais);
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
			Pais pais = entityManager.find(Pais.class, pk);
			entityManager.remove(pais);
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
	public boolean actualizar(Pais pais) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.merge(pais); /*MODIFICA*/
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
	public Pais buscar(Object pk) {
	    EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            return em.find(Pais.class, pk);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            em.close();
	            emf.close();
	        }
	}

	@Override
	public ArrayList<Pais> listarTodos() {
	    EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            List<Pais> lista =
	                em.createNamedQuery("Pais.findAll", Pais.class)
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
