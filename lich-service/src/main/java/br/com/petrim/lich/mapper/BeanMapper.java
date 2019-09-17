package br.com.petrim.lich.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Component
public class BeanMapper {

    private static MapperFactory factory;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private AbstractMapper<?, ?>[] mappers;

    @PostConstruct
    protected void configureMapper() {
        setFactory(this.beanFactory.getFactory());

        for(AbstractMapper<?, ?> mapper : mappers) {
            factory.registerClassMap(mapper.getClassMap());
        }

        configureConverter();
    }

    private void configureConverter() {
        ConverterFactory converterFactory = factory.getConverterFactory();
//        converterFactory.registerConverter("Job_JobStep_Converter", new BagSetConverter(JobStep.class, JobStepDto.class));
//        converterFactory.registerConverter("Execution_Plan_Job_Converter", new BagSetConverter(ExecutionPlanJob.class, ExecutionPlanJobDto.class));
    }

    private static synchronized void setFactory(MapperFactory mapperFactory) {
        factory = mapperFactory;
    }

    public MapperFacade getMapper() {
        return factory.getMapperFacade();
    }

    public static <T extends Object> T convert(Object value, Class<T> clazz) {
        return factory.getMapperFacade().map(value, clazz);
    }

    public static <T extends Object> Collection<T> convertList(Iterable<?> value, Class<T> clazz) {
        return factory.getMapperFacade().mapAsList(value, clazz);
    }


}
