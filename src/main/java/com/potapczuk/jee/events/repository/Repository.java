package com.potapczuk.jee.events.repository;

import com.potapczuk.jee.events.entity.Identifiable;

public interface Repository<T extends Identifiable> {
	Class<T> getType();
	
	T store(T entity);
	
	T get(Long id);
	
	void remove(T entity);
}
