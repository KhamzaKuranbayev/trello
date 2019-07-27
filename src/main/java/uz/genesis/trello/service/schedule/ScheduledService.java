package uz.genesis.trello.service.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ScheduledService implements IScheduledService {
    private final EntityManager entityManager;
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public ScheduledService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void unblockUsers() {
        logger.info("UNBLOCK USERS");
        entityManager.createStoredProcedureQuery("unblockUser").execute();

    }
}
