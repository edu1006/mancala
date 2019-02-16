package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.Parameter;

import java.util.List;

public interface ParameterRepositoryCustom {

    Long countByFilter(Parameter filter);

    List<Parameter> findByFilter(Parameter filter, Integer page, Integer max);

}
