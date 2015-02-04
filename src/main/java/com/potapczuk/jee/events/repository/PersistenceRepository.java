package com.potapczuk.jee.events.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.potapczuk.jee.events.entity.Identifiable;

public abstract class PersistenceRepository<T extends Identifiable> implements
		Repository<T> {

	@Inject
	protected EntityManager em;

	private Class<T> type;

	public PersistenceRepository(Class<T> type) {
		this.type = type;
	}

	@Override
	public Class<T> getType() {
		return type;
	}

	@Override
	public T store(T entity) {
		T merged = merge(entity);
		em.persist(merged);
		return merged;
	}

	@Override
	public T get(Long id) {
		return em.find(type, id);
	}

	@Override
	public void remove(T entity) {
		em.remove(merge(entity));
	}

	private T merge(T entity) {
		return em.merge(entity);
	}

	protected EntityManager getManager() {
		return em;
	}
}
