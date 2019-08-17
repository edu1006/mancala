package br.com.petrim.lich.service;

import br.com.petrim.lich.model.User;
import br.com.petrim.lich.vo.UserStatusVo;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findById(Long id);

    Optional<User> findByLogin(String login);

    User save(User user);

    Long countByFilter(User filter);

    List<User> findByFilter(User filter, Integer page, Integer max);

    void definePassword(User user);

    void enableDisable(UserStatusVo userStatusVo);

}
