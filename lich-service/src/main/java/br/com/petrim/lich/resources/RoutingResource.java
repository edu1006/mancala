package br.com.petrim.lich.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoutingResource {

    @RequestMapping(value = {
            "/home",
            "/administration/**/{[path:[^\\\\.]*}"
    })
    public String redirect() {
        return "forward:/";
    }

}
