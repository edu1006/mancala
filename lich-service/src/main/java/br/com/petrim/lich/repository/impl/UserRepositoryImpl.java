package br.com.petrim.lich.repository.impl;

import br.com.petrim.lich.model.User;
import br.com.petrim.lich.repository.UserRepositoryCustom;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl extends AbstractRepositoryImpl<User> implements UserRepositoryCustom {

    @Override
    public Long countByFilter(User filter) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("Select count(u) from User u ");
        filterQuery(hql, params, filter);

        return findSingleResultClass(hql.toString(), params, Long.class);
    }

    @Override
    public List<User> findByFilter(User filter, Integer page, Integer max) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("Select new br.com.petrim.lich.model.User(u.id, u.name, u.login, u.phone, u.phoneCel, u.status) ");
        hql.append("From User u ");
        filterQuery(hql, params, filter);

        return findByPagination(hql.toString(), params, page, max);
    }

    private void filterQuery(StringBuilder hql, Map<String, Object> params, User filter) {
        if (filter.getStatus() != null) {
            hql.append("where u.status = :status ");
            params.put("status", filter.getStatus());
        } else {
            hql.append("where u.status is not null ");
        }

        if (StringUtils.isNotBlank(filter.getName())) {
            hql.append("and u.name like :name ");
            params.put("name", "%" + filter.getName() + "%");
        }

        if (StringUtils.isNotBlank(filter.getLogin())) {
            hql.append("and u.login like :login ");
            params.put("login", "%" + filter.getLogin() + "%");
        }

        if (StringUtils.isNotBlank(filter.getRegistration())) {
            hql.append("and u.registration = :registration ");
            params.put("registration", filter.getRegistration());
        }
    }
}
