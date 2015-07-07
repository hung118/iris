package gov.utah.iris.repository;

import gov.utah.iris.common.Constants;
import gov.utah.iris.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseRepository<T extends BaseModel<?>> implements CrudRepository<T, Long> {

	private static final int PAGE_SIZE = 500;

	/** The Constant logger. */
	protected final Logger logger = Logger.getLogger(getClass());

	/**
	 * EntityManager property for query execution purposes
	 */
	@PersistenceContext
	protected EntityManager	entityManager;

	/**
	 * the class of the entity
	 */
	private final Class<T>	type;
	
	protected Pageable defaultPageable = new PageRequest(0, PAGE_SIZE);

	public BaseRepository(Class<T> type) {
		this.type = type;
	}

	/**
	 * Generic save method, calls the merge() method
	 * @param <S>
	 * @param entity
	 * @return
	 */
	@Override
	@Modifying
	public <S extends T> S save(S entity) {

		if (entity != null) {
			if (entity.isPersisted()) {
				// if it already has an id/isPersisted, then we merge
				entity = entityManager.merge(entity);
			} else {
				// create the new entity in the data store.
				entityManager.persist(entity);
			}
		}

		return entity;
	}

	/**
	 * Save a collection of entities. This method iterates through the list and calls the save() method for each one
	 * The returned list contains a saved(merged) entity
	 * @param <S>
	 * @param entities
	 * @return
	 */
	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		List<S> list = new ArrayList<S>();
		for (S s : entities) {
			list.add(this.save(s));
		}
		return list;
	}

	/**
	 * Find a single entity based on the ID (expected that the ID is a primary/unique key)
	 * If the entity does not exist, then null is returned
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T findOne(Long id) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<T> root = criteriaQuery.from(type);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
		Query query = entityManager.createQuery(criteriaQuery);

		T result;
		try {
			result = (T) query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info(Constants.EXCEPTION_MSG_NO_RESULT + id, nre);
			result = null;
		}

		return result;
	}

	/**
	 * Check to see if an entity exists
	 * @param id
	 * @return
	 */
	@Override
	public boolean exists(Long id) {
		return findOne(id) != null;
	}

	/**
	 * Retrieve all entities
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Iterable<T> findAll() {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
		criteriaQuery.from(type);
		Query query = entityManager.createQuery(criteriaQuery);

		return query.getResultList();
	}

	/**
	 * Retrieve a collection of entities for a given set of ids
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Iterable<T> findAll(Iterable<Long> ids) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<T> root = criteriaQuery.from(type);
		In<Object> in = criteriaBuilder.in(root.get("id"));
		for (Long id : ids) {
			in = in.value(id);
		}
		criteriaQuery.where(in);
		Query query = entityManager.createQuery(criteriaQuery);

		return query.getResultList();
	}

	/**
	 * Provide a count of all entities
	 * @return
	 */
	@Override
	public long count() {
		List<T> all = (List<T>) this.findAll();
		return all != null ? all.size() : 0L;
	}

	/**
	 * Delete a collection of provided entities
	 * 
	 * @param entities
	 */
	@Override
	public void delete(Iterable<? extends T> entities) {
		for (T entity : entities) {
			this.delete(entity);
		}
	}

	/**
	 * Delete all entities
	 * Are you certain this is a good idea?
	 */
	@Override
	public void deleteAll() {
		List<T> all = (List<T>) this.findAll();
		for (T entity : all) {
			this.delete(entity);
		}
	}

	/**
	 * Delete an entity for a given id (assumes that the id is a unique key)
	 * @param id
	 */
	@Override
	public void delete(Long id) {
		T entity = this.findOne(id);
		if (entity != null) {
			this.delete(entity);
		}
	}

	/**
	 * Delete an entity
	 * @param entity
	 */
	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
		entityManager.flush();
	}


}
