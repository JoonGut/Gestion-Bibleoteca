package modelo_DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Libro;
import model.Pais;
import model.Poblacion;

public class Poblacion_DAO implements Patron_DAO<Poblacion, Integer>{

	
	@Override
	public boolean insertar(Poblacion poblacion) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(poblacion);
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
			Poblacion poblacion = entityManager.find(Poblacion.class, pk);
			entityManager.remove(poblacion);
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
	public boolean actualizar(Poblacion poblacion) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.merge(poblacion); /*MODIFICA*/
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
	public Poblacion buscar(Object pk) {
		 EntityManagerFactory emf =
		            Persistence.createEntityManagerFactory("gestion_bibleoteca");
		        EntityManager em = emf.createEntityManager();

		        try {
		            return em.find(Poblacion.class, pk);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        } finally {
		            em.close();
		            emf.close();
		        }
	}

	@Override
	public ArrayList<Poblacion> listarTodos() {
	    EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            List<Poblacion> lista =
	                em.createNamedQuery("Poblacion.findAll", Poblacion.class)
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
