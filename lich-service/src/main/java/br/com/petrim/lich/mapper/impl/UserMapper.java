package br.com.petrim.lich.mapper.impl;

import br.com.petrim.lich.mapper.AbstractMapper;
import br.com.petrim.lich.model.User;
import br.com.petrim.lich.vo.UserVo;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserVo> {

    public UserMapper() {
        super(User.class, UserVo.class);
    }

    @Override
    protected void configure(ClassMapBuilder<User, UserVo> builder) {
        builder.byDefault();
    }
}
