package org.openmrs.module.inpatient.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.inpatient.Discharge;
import org.openmrs.module.inpatient.api.db.DischargeDAO;

import java.util.List;

/**
 * Created by banga on 5/28/15.
 */
public class HibernateDischargeDAO implements DischargeDAO {

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
    public List<Discharge> getAllDischarge() {
        return sessionFactory.getCurrentSession().createCriteria(Discharge.class).list();
    }

    @Override
    public Discharge getDischarge(Integer dischargeId) {
        return (Discharge) sessionFactory.getCurrentSession().get(Discharge.class, dischargeId);
    }

    @Override
    public Discharge saveDischarge(Discharge discharge) {

        sessionFactory.getCurrentSession().save(discharge);
        return discharge;
    }

    @Override
    public void purgeDischarge(Discharge discharge) {
        sessionFactory.getCurrentSession().delete(discharge);

    }
}
