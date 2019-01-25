package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.Group;

import java.util.List;

public interface GroupRepositoryCustom {

    Long countByFilter(Group filter);

    List<Group> findByFilter(Group filter, Integer page, Integer max);

}
