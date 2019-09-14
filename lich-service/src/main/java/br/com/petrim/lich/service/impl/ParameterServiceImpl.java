package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.enums.StatusEnum;
import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.model.Parameter;
import br.com.petrim.lich.repository.ParameterRepository;
import br.com.petrim.lich.service.ParameterService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("parameterService")
public class ParameterServiceImpl extends AbstractService implements ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    @Override
    public Parameter save(Parameter parameter) {

        if (parameter.getId() == null) {
            validateParameterExists(parameter);
        } else {
            checkStatusParameterExists(parameter);
        }

        return parameterRepository.save(parameter);
    }

    private void checkStatusParameterExists(Parameter parameter) {
        StatusEnum statusDB = parameterRepository.findStatusById(parameter.getId());

        if (StatusEnum.DISABLED.equals(statusDB) && StatusEnum.ENABLED.equals(parameter.getStatus())) {
            validateParameterExists(parameter);
        }
    }

    private void validateParameterExists(Parameter parameter) {
        if (parameterRepository.countByNameAndStatus(parameter.getName(), StatusEnum.ENABLED).compareTo(NumberUtils.LONG_ZERO) > 0) {
            throw new BusinessException("parameter.exists");
        }
    }

    @Override
    public Long countByFilter(Parameter filter) {
        return parameterRepository.countByFilter(filter);
    }

    @Override
    public List<Parameter> findByFilter(Parameter filter, Integer page, Integer max) {
        return parameterRepository.findByFilter(filter, page, max);
    }

    @Override
    public List<Parameter> findEnabled() {
        return parameterRepository.findByStatus(StatusEnum.ENABLED);
    }

    @Override
    public Map<String, Parameter> findEnabledValued() {
        List<Parameter> parameters = parameterRepository.findValuesByStatus(StatusEnum.ENABLED);
        return parameters.stream().collect(Collectors.toMap(Parameter::getName, Function.identity()));
    }
}
