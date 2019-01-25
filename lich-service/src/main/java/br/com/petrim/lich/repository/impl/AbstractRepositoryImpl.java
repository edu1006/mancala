package br.com.petrim.lich.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class AbstractRepositoryImpl<T> {

    @Autowired
    private EntityManager entityManager;

    protected <E extends Object> E findSingleResultClass(String hql, Map<String, Object> params, Class<E> resultClass) {
        Query query = getEntityManager().createQuery(hql, resultClass);
        setParamsOnQuery(query, params);
        return (E) query.getSingleResult();
    }

    protected List<T> findByPagination(String hql, Map<String, Object> params, Integer first, Integer max) {
        Query query = getEntityManager().createQuery(hql);

        setParamsOnQuery(query, params);

        query.setFirstResult(first);
        query.setMaxResults(max);

        return query.getResultList();
    }

    protected void setParamsOnQuery(Query query, Map<String, Object> params) {
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

}
