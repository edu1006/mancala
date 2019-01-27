package br.com.petrim.lich.repository.impl;

import br.com.petrim.lich.model.Agent;
import br.com.petrim.lich.repository.AgentRepositoryCustom;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentRepositoryImpl extends AbstractRepositoryImpl<Agent> implements AgentRepositoryCustom {

    @Override
    public Long countByFilter(Agent filter) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("select count(*) from Agent a ");
        filterQuery(hql, params, filter);

        return findSingleResultClass(hql.toString(), params, Long.class);
    }

    @Override
    public List<Agent> findByFilter(Agent filter, Integer page, Integer max) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("select a from Agent a ");
        filterQuery(hql, params, filter);

        return findByPagination(hql.toString(), params, page, max);
    }

    private void filterQuery(StringBuilder hql, Map<String, Object> params, Agent filter) {
        hql.append("where a.status is not null ");

        if (StringUtils.isNotBlank(filter.getName())) {
            hql.append("and a.name like :name ");
            params.put("name", "%" + filter.getName() + "%");
        }

        if (filter.getType() != null) {
            hql.append("and a.type = :type ");
            params.put("type", filter.getType());
        }

        if (filter.getStatus() != null) {
            hql.append("and a.status = :status ");
            params.put("status", filter.getStatus());
        }
    }
}
