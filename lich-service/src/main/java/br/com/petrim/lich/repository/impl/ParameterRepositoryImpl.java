package br.com.petrim.lich.repository.impl;

import br.com.petrim.lich.model.Parameter;
import br.com.petrim.lich.repository.ParameterRepositoryCustom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterRepositoryImpl extends AbstractRepositoryImpl<Parameter> implements ParameterRepositoryCustom {

    @Override
    public Long countByFilter(Parameter filter) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("Select count(p.id) From Parameter p ");
        filterQuery(filter, hql, params);

        return findSingleResultClass(hql.toString(), params, Long.class);
    }

    @Override
    public List<Parameter> findByFilter(Parameter filter, Integer page, Integer max) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("Select p From Parameter p ");
        filterQuery(filter, hql, params);

        return findByPagination(hql.toString(), params, page, max);
    }

    private void filterQuery(Parameter filter, StringBuilder hql, Map<String, Object> params) {
        hql.append("where p.status is not null ");
    }
}
