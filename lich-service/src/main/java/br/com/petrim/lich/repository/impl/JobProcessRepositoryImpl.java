package br.com.petrim.lich.repository.impl;

import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.repository.JobProcessRepositoryCustom;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobProcessRepositoryImpl extends AbstractRepositoryImpl<JobProcess> implements JobProcessRepositoryCustom {

    @Override
    public Long countByFilter(JobProcess filter) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("Select count(j.id) from JobProcess j ");
        filterQuery(hql, params, filter);

        return findSingleResultClass(hql.toString(), params, Long.class);
    }

    @Override
    public List<JobProcess> findByFilter(JobProcess filter, Integer page, Integer max) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("Select new br.com.petrim.lich.model.JobProcess(j.id, j.idProcess, j.typeExecution, j.status) ");
        hql.append("from JobProcess j ");
        filterQuery(hql, params, filter);

        return findByPagination(hql.toString(), params, page, max);
    }

    private void filterQuery(StringBuilder hql, Map<String, Object> params, JobProcess filter) {
        hql.append("where j.status is not null ");

        if (StringUtils.isNotBlank(filter.getIdProcess())) {
            hql.append("and j.idProcess like :idProcess ");
            params.put("idProcess", "%" + filter.getIdProcess() + "%");
        }

        if (filter.getTypeExecution() != null) {
            hql.append("and j.typeExecution = :typeExecution ");
            params.put("typeExecution", filter.getTypeExecution());
        }

        if (filter.getStatus() != null) {
            hql.append("and j.status = :status ");
            params.put("status", filter.getStatus());
        }

        if (StringUtils.isNotBlank(filter.getTags())) {
            hql.append("and j.tags like :tags ");
            params.put("tags", "%" + filter.getTags() + "%");
        }
    }
}
