package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.model.Audit;
import br.com.petrim.lich.repository.AuditRepository;
import br.com.petrim.lich.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("auditService")
public class AuditServiceImpl extends AbstractService implements AuditService {

    @Autowired
    private AuditRepository auditRepository;

    @Override
    public synchronized void save(Audit audit) {

        //FIXME: verificar se existe usuário logado.

        auditRepository.save(audit);
    }
}
