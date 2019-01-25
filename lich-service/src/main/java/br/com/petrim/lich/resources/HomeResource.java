package br.com.petrim.lich.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/home")
public class HomeResource {

    @RequestMapping(method = RequestMethod.GET)
    public String getMessage() {
        return "{\"message\": \"Hello World Test\"}";
    }

}
