package br.com.petrim.lich.listener;

import br.com.petrim.lich.enums.TypeTransactionEnum;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InsertListener extends EventListener implements PostInsertEventListener {

    @PostConstruct
    public void init() {
        registry(this, EventType.POST_INSERT);
    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        audit(postInsertEvent.getEntity(), TypeTransactionEnum.INSERT);
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}
