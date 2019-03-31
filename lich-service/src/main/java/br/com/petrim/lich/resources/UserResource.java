package br.com.petrim.lich.resources;

import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.model.User;
import br.com.petrim.lich.security.AuthUserDetails;
import br.com.petrim.lich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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
        Long userId = ((AuthUserDetails) ((OAuth2Authentication) principal).getUserAuthentication().getPrincipal()).getUser().getId();
        return userService.findById(userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
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

    @RequestMapping(value = "/definePassword", method = RequestMethod.POST)
    public void definePassword(@RequestBody User user) {
        userService.definePassword(user);
    }

}
