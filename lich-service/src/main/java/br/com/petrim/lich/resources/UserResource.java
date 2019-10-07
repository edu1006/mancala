package br.com.petrim.lich.resources;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.petrim.lich.model.User;
import br.com.petrim.lich.security.AuthUserDetails;
import br.com.petrim.lich.service.UserService;
import br.com.petrim.lich.util.ConstantsRoles;
import br.com.petrim.lich.vo.UserStatusVo;
import br.com.petrim.lich.vo.UserVo;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserResource extends AbstractResource {

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

    @Secured(ConstantsRoles.ROLE_USER)
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long countByFilter(@RequestBody UserVo filter) {
        return userService.countByFilter(convert(filter, User.class));
    }

    @Secured(ConstantsRoles.ROLE_USER)
    @RequestMapping(value = "/find/{page}/{max}")
    public List<User> findByFilter(@RequestBody UserVo filter, @PathVariable("page") Integer page, @PathVariable("max") Integer max) {
        return userService.findByFilter(convert(filter, User.class), page, max);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User save(@RequestBody UserVo user) {
        return userService.save(convert(user, User.class));
    }

    @RequestMapping(value = "/definePassword", method = RequestMethod.POST)
    public void definePassword(@RequestBody UserVo user) {
        userService.definePassword(convert(user, User.class));
    }

    @RequestMapping(value = "/enableDisable", method = RequestMethod.POST)
    public void enableDisable(@RequestBody UserStatusVo userStatusVo) {
        userService.enableDisable(userStatusVo);
    }

}
