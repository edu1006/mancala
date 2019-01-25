package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.User;

import java.util.List;

public interface UserRepositoryCustom {

    Long countByFilter(User filter);

    List<User> findByFilter(User user, Integer page, Integer max);

}
