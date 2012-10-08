package com.chinarewards.license.common;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BaseDao<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected final String SEARCH = "SEARCH";
	protected final String COUNT = "COUNT";

	@Inject
	private Provider<EntityManager> entityManager;

	/**
	 * 
	 * 
	 * @return
	 */
	public EntityManager getEm() {
		return entityManager.get();
	}

	/**
	 * persist T to DB .
	 * 
	 * @param t
	 *            one of your entity bean instance
	 */
	public T save(T t) {
		getEm().persist(t);

		return t;
	}

	/**
	 * meger modification to DB.
	 * 
	 * @param t
	 *            one of your entity bean instance
	 */
	public T update(T t) {
		getEm().merge(t);

		return t;
	}

	/**
	 * 
	 * delete t to DB.
	 * 
	 * @param t
	 */
	public void delete(T t) {
		getEm().remove(t);
	}

	/**
	 * find object by it's primary key .
	 * 
	 * @param T
	 *            t one of your entity bean instance
	 * @param id
	 *            primary key value
	 * 
	 * @return Object(T)
	 * 
	 */
	public T findById(Class<T> entityClass, Object id) {
		return (T) getEm().find(entityClass, id);
	}
}
