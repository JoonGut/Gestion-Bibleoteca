package modelo_DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Autor;
import model.Categoria;

public class Categoria_DAO implements Patron_DAO<Categoria, Integer> {

	@Override
	public boolean insertar(Categoria categoria) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(categoria);
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
			Categoria categoria = entityManager.find(Categoria.class, pk);
			entityManager.remove(categoria);
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
	public boolean actualizar(Categoria categoria) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestion_bibleoteca");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.merge(categoria); /*MODIFICA*/
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
	public Categoria buscar(Object pk) {
	    EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            return em.find(Categoria.class, pk);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            em.close();
	            emf.close();
	        }
	}

	@Override
	public ArrayList<Categoria> listarTodos() {
		EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("gestion_bibleoteca");
	        EntityManager em = emf.createEntityManager();

	        try {
	            List<Categoria> lista =
	                em.createNamedQuery("Categoria.findAll", Categoria.class)
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
