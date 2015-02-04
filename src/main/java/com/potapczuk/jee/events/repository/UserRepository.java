package com.potapczuk.jee.events.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Typed;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.potapczuk.jee.events.entity.User;

@Stateless
@Typed(UserRepository.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserRepository extends PersistenceRepository<User> {
	
	public UserRepository()
	{
		super(User.class);
	}

	public List<User> findAllOrderedByName() {
		List<User> users;
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> criteria = cb.createQuery(User.class);
			Root<User> user = criteria.from(User.class);
//			criteria.select(user).orderBy(cb.asc(user.get(User_.name)));
			criteria.select(user).orderBy(cb.asc(user.get("name")));
			
			users = em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			throw new RuntimeException("[User] Error to findAllOrderedByName", e);
		}
		
		return users;
	}
}
