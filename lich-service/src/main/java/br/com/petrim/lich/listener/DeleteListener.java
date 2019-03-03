package br.com.petrim.lich.listener;

import br.com.petrim.lich.enums.TypeTransactionEnum;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DeleteListener extends EventListener implements PostDeleteEventListener {

    @PostConstruct
    public void init() {
        registry(this, EventType.POST_DELETE);
    }

    @Override
    public void onPostDelete(PostDeleteEvent postDeleteEvent) {
        audit(postDeleteEvent.getEntity(), TypeTransactionEnum.DELETE);
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}
