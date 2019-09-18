package br.com.petrim.lich.mapper.impl;

import br.com.petrim.lich.mapper.AbstractMapper;
import br.com.petrim.lich.model.Parameter;
import br.com.petrim.lich.vo.ParameterVo;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class ParameterMapper extends AbstractMapper<Parameter, ParameterVo> {

    public ParameterMapper() {
        super(Parameter.class, ParameterVo.class);
    }

    @Override
    protected void configure(ClassMapBuilder<Parameter, ParameterVo> builder) {
        builder.byDefault();
    }
}
