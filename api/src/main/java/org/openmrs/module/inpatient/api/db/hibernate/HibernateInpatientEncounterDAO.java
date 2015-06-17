package org.openmrs.module.inpatient.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.inpatient.InpatientEncounter;
import org.openmrs.module.inpatient.api.db.InpatientEncounterDAO;

import java.util.List;

/**
 * Created by banga on 6/16/15.
 */
public class HibernateInpatientEncounterDAO implements InpatientEncounterDAO {
    protected final Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    @Override
    public List<InpatientEncounter> getAllInpatientEncounters() {
        return sessionFactory.getCurrentSession().createCriteria(InpatientEncounter.class).list();
    }

    @Override
    public InpatientEncounter getInpatientEncounter(Integer encounterId) {
        return (InpatientEncounter)sessionFactory.getCurrentSession().get(InpatientEncounter.class, encounterId);
    }

    @Override
    public InpatientEncounter saveInpatientEncounter(InpatientEncounter encounter) {
        sessionFactory.getCurrentSession().save(encounter);
        return encounter;
    }

    @Override
    public void purgeInpatientEncounter(InpatientEncounter encounter) {
        sessionFactory.getCurrentSession().delete(encounter);
    }
}
