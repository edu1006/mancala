package br.com.petrim.lich.listener;

import br.com.petrim.lich.enums.TypeTransactionEnum;
import br.com.petrim.lich.model.Audit;
import br.com.petrim.lich.service.AuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public abstract class EventListener {

    @Autowired
    private AuditService auditService;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    protected void registry(EventListener listener, EventType type) {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);

        registry.getEventListenerGroup(type).appendListener(listener);
    }

    protected void audit(Object entity, TypeTransactionEnum typeTransaction) {
        if (!entity.getClass().equals(Audit.class)) {
            Audit audit = new Audit();

            // 0 - User.... 1 - System
            audit.setSystemTransaction(NumberUtils.INTEGER_ZERO);

            audit.setIp(getIpAddress());

            audit.setDateEvent(new Date());
            audit.setClassEntity(entity.getClass().getCanonicalName());
            audit.setJsonEntity(convertObjectToJson(entity));
            audit.setTypeTransaction(typeTransaction);

            auditService.save(audit);
        }
    }

    private String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isBlank(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

    protected String convertObjectToJson(Object entity) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            getLogger().error("Error to convert object to json", e);
        }
        return json;
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

}
