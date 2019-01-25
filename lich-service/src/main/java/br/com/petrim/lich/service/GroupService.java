package br.com.petrim.lich.service;

import br.com.petrim.lich.model.Group;

import java.util.List;

public interface GroupService {

    Group save(Group group);

    Long countByFilter(Group filter);

    List<Group> findByFilter(Group filter, Integer page, Integer max);

    List<Group> findEnabled();

}
