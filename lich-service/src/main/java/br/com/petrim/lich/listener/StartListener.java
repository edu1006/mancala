package br.com.petrim.lich.listener;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.com.petrim.lich.enums.FunctionalityEnum;
import br.com.petrim.lich.model.Functionality;
import br.com.petrim.lich.model.Group;
import br.com.petrim.lich.model.User;
import br.com.petrim.lich.repository.FunctionalityRepository;
import br.com.petrim.lich.repository.GroupRepository;
import br.com.petrim.lich.repository.UserRepository;
import br.com.petrim.lich.util.HashUtil;
import br.com.petrim.lich.util.SpringContextUtil;

@Component
public class StartListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartListener.class);

    private static final Long ID_ADMIN = 1L;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOGGER.info("-----------------------------LOAD ADMIN DATA--------------------------------------");
        saveAccessFunctionalilies();
        saveGroupAdmin();
        saveUserAdmin();
    }

    private void saveGroupAdmin() {
    	GroupRepository groupRepository = SpringContextUtil.getBean(GroupRepository.class);
    	
        Optional<Group> optionalGroup = groupRepository.findById(ID_ADMIN);
        if (!optionalGroup.isPresent()) {
            groupRepository.insertAdminGroup();
            LOGGER.info("-----------------------------SAVE ADMIN GROUP--------------------------------------");
        }
        
        updateGroupAdminFunctionalities(groupRepository, optionalGroup);
    }

    private void saveUserAdmin() {
    	UserRepository userRepository = SpringContextUtil.getBean(UserRepository.class);
    	
        Optional<User> optionalUser = userRepository.findById(ID_ADMIN);
        if (!optionalUser.isPresent()) {
            userRepository.insertAdminUser(new Date(), HashUtil.hashHexa("admin123", "SHA-256"));
            userRepository.insertAdminUserGroup();
            LOGGER.info("-----------------------------SAVE ADMIN USER--------------------------------------");
        }
    }
    
    private void saveAccessFunctionalilies() {
    	FunctionalityRepository functionalityRepository = SpringContextUtil.getBean(FunctionalityRepository.class);
    	
    	List<FunctionalityEnum> funcsEnums = Arrays.asList(FunctionalityEnum.values());
    	
    	funcsEnums.forEach(f -> {
    		if (!functionalityRepository.existsById(f.getId())) {
    			Functionality func = new Functionality(f.getId(), f.getName(), f.getParentId());
    			functionalityRepository.save(func);
    		}
    	});
    	
    	LOGGER.info("-----------------------------SAVE FUNCTIONALITIES--------------------------------------");
    }
    
    private void updateGroupAdminFunctionalities(GroupRepository groupRepository, Optional<Group> optionalGroup) {
    	if (optionalGroup.isPresent()) {
    		List<Long> functionalities = groupRepository.findAdminGroupFunctionalities();
    		
    		for (FunctionalityEnum functionalityEnum : FunctionalityEnum.values()) {
    			if (!functionalities.contains(functionalityEnum.getId())) {
    				groupRepository.insertAdminGroupFuncionality(functionalityEnum.getId());
    			}
    		}
    		
    	}
    	
    }
}
