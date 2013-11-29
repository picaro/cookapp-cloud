package com.op.cookcloud.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.op.cookcloud.model.base.EntityWithId;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public abstract class EntityWithIdDao<T extends EntityWithId> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<?> clazz;

    public EntityWithIdDao() {
        Type t = getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) t;
            clazz = (Class<?>) paramType.getActualTypeArguments()[0];
        }
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public T save(T entity) {
        return (T) sessionFactory.getCurrentSession().merge(entity);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public T getById(int id) {
        T result = (T) sessionFactory.getCurrentSession().get(clazz, id);
        if (result == null) {
            throw new EntityNotFoundException("Could not get entity with id " + id);
        }
        return result;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public T getOneResultByParameter(String fieldName, Object parameter) {
        T result = (T) sessionFactory.getCurrentSession().createCriteria(clazz).add(Restrictions.eq(fieldName, parameter)).uniqueResult();
        if (result == null) {
            throw new EntityNotFoundException("Could not get entity " + clazz.getSimpleName() + " for " + fieldName + "=" + parameter);
        }
        return result;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getResultListByParameter(String fieldName, Object parameter) {
        List<T> result = sessionFactory.getCurrentSession().createCriteria(clazz).add(Restrictions.eq(fieldName, parameter)).list();
        return result;
    }

    @Transactional
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<T> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(clazz).list();
    }

    @Transactional
    protected List<T> getPageWithCriteria(Criteria criteria, int pageNum, int itemsPerPage) {

        return null;
    }

    protected long getPageCount(Criteria criteria, int itemsPerPage) {
        return 0;
    }

    protected Class<?> getClazz() {
        return clazz;
    }

}
