package br.com.petrim.lich.resources;

import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.model.User;
import br.com.petrim.lich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public User getUser(Principal principal) {
        Optional<User> optional = userService.findByLogin(principal.getName());

        if (optional.isPresent()) {
            User user = optional.get();
            user.setPassword(null);

            return user;
        }

        throw new BusinessException("user.token.invalid");
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long countByFilter(@RequestBody User filter) {
        return userService.countByFilter(filter);
    }

    @RequestMapping(value = "/find/{page}/{max}")
    public List<User> findByFilter(@RequestBody User filter, @PathVariable("page") Integer page, @PathVariable("max") Integer max) {
        return userService.findByFilter(filter, page, max);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

}
