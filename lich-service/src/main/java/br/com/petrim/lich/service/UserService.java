package br.com.petrim.lich.service;

import br.com.petrim.lich.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByLogin(String login);

    User save(User user);

    Long countByFilter(User filter);

    List<User> findByFilter(User filter, Integer page, Integer max);

}
