package br.com.petrim.lich.mapper.impl;

import br.com.petrim.lich.mapper.AbstractMapper;
import br.com.petrim.lich.model.Group;
import br.com.petrim.lich.vo.GroupVo;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper extends AbstractMapper<Group, GroupVo> {

    public GroupMapper() {
        super(Group.class, GroupVo.class);
    }

    @Override
    protected void configure(ClassMapBuilder<Group, GroupVo> builder) {
        builder.byDefault();
    }
}
