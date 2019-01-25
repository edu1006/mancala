package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.model.User;
import br.com.petrim.lich.repository.UserRepository;
import br.com.petrim.lich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            verifyUserExists(user.getLogin());
            user.setDateInsert(new Date());
        }
        return userRepository.save(user);
    }

    private void verifyUserExists(String login) {
        if (userRepository.existsByLogin(login)) {
            throw new BusinessException("user.exists");
        }
    }

    @Override
    public Long countByFilter(User filter) {
        return userRepository.countByFilter(filter);
    }

    @Override
    public List<User> findByFilter(User filter, Integer page, Integer max) {
        return userRepository.findByFilter(filter, page, max);
    }
}
