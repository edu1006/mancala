package br.com.petrim.lich.service;

import br.com.petrim.lich.model.StepProtocol;

import java.util.Date;

public interface StepProtocolService {

    StepProtocol save(StepProtocol stepProtocol);

    void update(Long id, Long idStepProcess, String status, Date dateEnd, String errorMessage);

}
