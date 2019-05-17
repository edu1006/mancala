package br.com.petrim.lich.listener;

import br.com.petrim.lich.enums.StatusEnum;
import br.com.petrim.lich.model.Group;
import br.com.petrim.lich.model.User;
import br.com.petrim.lich.repository.GroupRepository;
import br.com.petrim.lich.repository.UserRepository;
import br.com.petrim.lich.util.HashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Component
public class StartListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartListener.class);

    private static final Long ID_ADMIN = 1L;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOGGER.info("-----------------------------LOAD ADMIN DATA--------------------------------------");
        saveGroupAdmin();
        saveUserAdmin();
    }

    private void saveGroupAdmin() {
        Optional<Group> optionalGroup = groupRepository.findById(ID_ADMIN);
        if (!optionalGroup.isPresent()) {
            groupRepository.insertAdminGroup();
            LOGGER.info("-----------------------------SAVE ADMIN GROUP--------------------------------------");
        }
    }

    private void saveUserAdmin() {
        Optional<User> optionalUser = userRepository.findById(ID_ADMIN);
        if (!optionalUser.isPresent()) {
            userRepository.insertAdminUser(new Date(), HashUtil.hashHexa("admin123", "SHA-256"));
            userRepository.insertAdminUserGroup();
            LOGGER.info("-----------------------------SAVE ADMIN USER--------------------------------------");
        }
    }
}
