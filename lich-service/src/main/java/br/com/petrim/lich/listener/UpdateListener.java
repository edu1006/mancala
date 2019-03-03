package br.com.petrim.lich.listener;

import br.com.petrim.lich.enums.TypeTransactionEnum;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UpdateListener extends EventListener implements PostUpdateEventListener {

    @PostConstruct
    public void init() {
        registry(this, EventType.POST_UPDATE);
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        audit(postUpdateEvent.getEntity(), TypeTransactionEnum.UPDATE);
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}
