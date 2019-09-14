package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.model.StepProtocol;
import br.com.petrim.lich.repository.StepProtocolRepository;
import br.com.petrim.lich.service.StepProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("stepProtocolService")
public class StepProtocolServiceImpl extends AbstractService implements StepProtocolService {

    @Autowired
    private StepProtocolRepository stepProtocolRepository;

    @Override
    public StepProtocol save(StepProtocol stepProtocol) {
        return stepProtocolRepository.save(stepProtocol);
    }

    @Override
    public void update(Long id, Long idStepProcess, String status, Date dateEnd, String errorMessage) {
        stepProtocolRepository.updateStatus(id, idStepProcess, status, dateEnd, errorMessage);
    }
}
