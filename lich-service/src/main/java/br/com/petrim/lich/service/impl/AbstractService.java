package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.model.AbstractUserHistEntity;
import br.com.petrim.lich.model.User;
import br.com.petrim.lich.repository.UserRepository;
import br.com.petrim.lich.security.AuthUserDetails;
import br.com.petrim.lich.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.Optional;

public abstract class AbstractService {

    protected Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

    protected User getAuthUser() {
        String login = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        UserRepository userRepository = SpringContextUtil.getBean(UserRepository.class);

        Optional<User> optional = userRepository.findByLogin(login);

        if (!optional.isPresent()) {
            throw new BusinessException("user.not.exists");
        }

        return optional.get();
    }

    protected void loadUserInsertUpdate(AbstractUserHistEntity entity) {
        if (entity.getId() == null) {
            entity.setDateInsert(new Date());
            entity.setUserInsert(getAuthUser().getId());
        } else {
            entity.setDateUpdate(new Date());
            entity.setUserUpdate(getAuthUser().getId());
        }
    }

}
