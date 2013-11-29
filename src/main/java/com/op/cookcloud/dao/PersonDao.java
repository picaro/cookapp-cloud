package com.op.cookcloud.dao;

import javax.persistence.EntityNotFoundException;

import com.op.cookcloud.model.base.Person;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

public class PersonDao extends EntityWithIdDao<Person> {
	
	@Transactional
	public Person getPersonByID(String id) {
		Person result = (Person) sessionFactory.getCurrentSession()
			.createCriteria(Person.class)
			.add(Restrictions.eq("id", id))
			.uniqueResult();
		if (result == null) {
			throw new EntityNotFoundException();
		}
		return result;
	}

}
