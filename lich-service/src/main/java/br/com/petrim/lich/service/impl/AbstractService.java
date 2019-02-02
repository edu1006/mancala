package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.model.AbstractUserHistEntity;
import br.com.petrim.lich.model.User;
import br.com.petrim.lich.security.AuthUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public abstract class AbstractService {

    protected Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

    protected User getAuthUser() {
        AuthUserDetails userDetails = (AuthUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getDetails();
        return userDetails.getUser();
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
