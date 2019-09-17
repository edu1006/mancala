package br.com.petrim.lich.mapper;

import ma.glasnost.orika.metadata.ClassMap;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapper<E, T> {

    @Autowired
    private BeanFactory factory;

    private Class<E> entityClass;
    private Class<T> dtoClass;

    protected AbstractMapper(Class<E> entityClass, Class<T> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public ClassMap<E, T> getClassMap() {
        ClassMapBuilder<E, T> builder = factory.getFactory().classMap(entityClass, dtoClass);
        configure(builder);
        return builder.toClassMap();
    }

    protected abstract void configure(ClassMapBuilder<E, T> builder);


}
