package br.com.petrim.lich.resources;

import br.com.petrim.lich.model.Group;
import br.com.petrim.lich.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/group", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class GroupResource {

    @Autowired
    private GroupService groupService;

    @RequestMapping(method = RequestMethod.PUT)
    public Group save(@RequestBody Group group) {
        return groupService.save(group);
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long countByFilter(@RequestBody Group filter) {
        return groupService.countByFilter(filter);
    }

    @RequestMapping(value = "/find/{page}/{max}", method = RequestMethod.POST)
    public List<Group> findByFilter(@RequestBody Group filter, @PathVariable("page") Integer page, @PathVariable("max") Integer max) {
        return groupService.findByFilter(filter, page, max);
    }

    @RequestMapping(value = "/find/enabled", method = RequestMethod.GET)
    public List<Group> findEnabled() {
        return groupService.findEnabled();
    }

}
