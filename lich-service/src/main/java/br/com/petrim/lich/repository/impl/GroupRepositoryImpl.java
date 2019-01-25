package br.com.petrim.lich.repository.impl;

import br.com.petrim.lich.model.Group;
import br.com.petrim.lich.repository.GroupRepositoryCustom;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRepositoryImpl extends AbstractRepositoryImpl<Group> implements GroupRepositoryCustom {

    @Override
    public Long countByFilter(Group filter) {
        StringBuilder hql = new StringBuilder("Select count(g) from Group g ");
        Map<String, Object> params = new HashMap<>();

        filterQuery(hql, params, filter);
        return findSingleResultClass(hql.toString(), params, Long.class);
    }

    @Override
    public List<Group> findByFilter(Group filter, Integer page, Integer max) {
        StringBuilder hql = new StringBuilder("Select g from Group g ");
        Map<String, Object> params = new HashMap<>();

        filterQuery(hql, params, filter);
        return findByPagination(hql.toString(), params, page, max);
    }

    private void filterQuery(StringBuilder hql, Map<String, Object> params, Group filter) {
        if (filter.getStatus() != null) {
            hql.append("where g.status = :status ");
            params.put("status", filter.getStatus());
        } else {
            hql.append("where g.status is not null ");
        }

        if (StringUtils.isNotBlank(filter.getName())) {
            hql.append("and g.name like :name ");
            params.put("name", "%" + filter.getName() + "%");
        }
    }
}
