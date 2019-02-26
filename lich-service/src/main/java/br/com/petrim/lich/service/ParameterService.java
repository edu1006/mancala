package br.com.petrim.lich.service;

import br.com.petrim.lich.model.Parameter;

import java.util.List;

public interface ParameterService {

    Parameter save(Parameter parameter);

    Long countByFilter(Parameter filter);

    List<Parameter> findByFilter(Parameter filter, Integer page, Integer max);

    List<Parameter> findEnabled();
}
