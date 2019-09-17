package br.com.petrim.lich.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class BeanFactory {

    private static MapperFactory factory;

    static {
        factory = new DefaultMapperFactory.Builder().build();
    }

    public synchronized  MapperFactory getFactory() {
        return factory;
    }

}
