package com.potapczuk.jee.events.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Typed;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.potapczuk.jee.events.entity.Event;

@Stateless
@Typed(EventRepository.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EventRepository extends PersistenceRepository<Event> {

	public EventRepository() {
		super(Event.class);
	}

	public List<Event> findAllOrderedByDate() {
		List<Event> events;
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Event> criteria = cb.createQuery(Event.class);
			Root<Event> event = criteria.from(Event.class);
//			criteria.select(event).orderBy(cb.asc(event.get(Event_.name)));
			criteria.select(event).orderBy(cb.asc(event.get("eventDate")));
			
			events = em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			throw new RuntimeException("[Event] Error to findAllOrderedByDate", e);
		}
		
		return events;
	}
}
