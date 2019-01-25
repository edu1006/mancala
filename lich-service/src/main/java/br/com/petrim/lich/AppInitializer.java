package br.com.petrim.lich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AppInitializer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppInitializer.class, args);
    }

}
