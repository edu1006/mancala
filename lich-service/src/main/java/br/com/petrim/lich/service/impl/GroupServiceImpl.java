package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.enums.StatusEnum;
import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.model.Group;
import br.com.petrim.lich.repository.GroupRepository;
import br.com.petrim.lich.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("groupService")
public class GroupServiceImpl extends AbstractService implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group save(Group group) {
        if (group.getId() == null) {
            verifyGroup(group.getName());
        }
        return groupRepository.save(group);
    }

    private void verifyGroup(String name) {
        if (groupRepository.existsByName(name)) {
            throw new BusinessException("group.exists");
        }
    }

    @Override
    public Long countByFilter(Group filter) {
        return groupRepository.countByFilter(filter);
    }

    @Override
    public List<Group> findByFilter(Group filter, Integer page, Integer max) {
        return groupRepository.findByFilter(filter, page, max);
    }

    @Override
    public List<Group> findEnabled() {
        return groupRepository.findByStatus(StatusEnum.ENABLED.getId());
    }
}
