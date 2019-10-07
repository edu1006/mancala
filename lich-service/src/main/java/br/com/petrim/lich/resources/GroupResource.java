package br.com.petrim.lich.resources;

import br.com.petrim.lich.model.Group;
import br.com.petrim.lich.service.GroupService;
import br.com.petrim.lich.util.ConstantsRoles;
import br.com.petrim.lich.vo.GroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/group", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class GroupResource extends AbstractResource {

    @Autowired
    private GroupService groupService;

    @RequestMapping(method = RequestMethod.PUT)
    public Group save(@RequestBody GroupVo group) {
        return groupService.save(convert(group, Group.class));
    }

    @Secured(ConstantsRoles.ROLE_GROUP)
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long countByFilter(@RequestBody GroupVo filter) {
        return groupService.countByFilter(convert(filter, Group.class));
    }

    @Secured(ConstantsRoles.ROLE_GROUP)
    @RequestMapping(value = "/find/{page}/{max}", method = RequestMethod.POST)
    public List<Group> findByFilter(@RequestBody GroupVo filter, @PathVariable("page") Integer page, @PathVariable("max") Integer max) {
        return groupService.findByFilter(convert(filter, Group.class), page, max);
    }

    @RequestMapping(value = "/find/enabled", method = RequestMethod.GET)
    public List<Group> findEnabled() {
        return groupService.findEnabled();
    }

}
