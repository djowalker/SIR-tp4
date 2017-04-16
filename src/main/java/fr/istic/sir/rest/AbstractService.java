package fr.istic.sir.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.hibernate.Criteria;
import org.hibernate.Session;

import domain.Person;
import servlet.EntitySingleton;


public abstract class AbstractService<T> {
	private Class<T> entityClass;

	public AbstractService(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public Response create(T entity) {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		try {
			if (!getEntityManager().getTransaction().isActive()) {
				getEntityManager().getTransaction().begin();
			}
			getEntityManager().persist(entity);
			EntitySingleton.commit();
			if (entity.getClass() == Person.class){
			return Response.created(null).entity(""+( (Person) entity).getId())
					.build();
			}
			else {
				return Response.created(null).entity("created")
						.build();
			}
		} catch (Exception ex) {
			EntitySingleton.rollback();
			throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
		}
	}

	public Response edit(T entity) {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		try {
			if (!getEntityManager().getTransaction().isActive()) {
				getEntityManager().getTransaction().begin();
			}
			getEntityManager().merge(entity);
			EntitySingleton.commit();
			return Response.ok().entity("Entity edited successfully").build();
		} catch (Exception ex) {
			EntitySingleton.rollback();
			throw new WebApplicationException(ex, Response.Status.NOT_FOUND);
		}
	}

	public Response remove(T entity) {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		try {
			if (!getEntityManager().getTransaction().isActive()) {
				getEntityManager().getTransaction().begin();
			}
			getEntityManager().remove(getEntityManager().merge(entity));
			EntitySingleton.commit();
			return Response.ok().entity("Entity deleted successfully").build();
		} catch (Exception ex) {
			EntitySingleton.rollback();
			throw new WebApplicationException(ex, Response.Status.NOT_FOUND);
		}
	}

	public T find(Object id) {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		T obj = getEntityManager().find(entityClass, id);
		EntitySingleton.closeEntityManager();
		return obj;
	}

	public List<T> findAll() {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		List<T> list = (List<T>) getEntityManager().createQuery(cq).getResultList();
		EntitySingleton.closeEntityManager();
		return list;
	}


	public int count() {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

}
