package org.openmrs.module.inpatient.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.inpatient.Ward;
import org.openmrs.module.inpatient.api.db.WardDAO;

import java.util.List;

/**
 * Created by banga on 5/26/15.
 */
public class HibernateWardDAO implements WardDAO {

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
    public List<Ward> getAllWards() {
        return sessionFactory.getCurrentSession().createCriteria(Ward.class).list();
    }

    @Override
    public Ward getWard(Integer wardId) {
        return (Ward) sessionFactory.getCurrentSession().get(Ward.class, wardId);
    }

    @Override
    public Ward saveWard(Ward ward) {
        sessionFactory.getCurrentSession().save(ward);
        return ward;
    }

    @Override
    public void purgeWard(Ward ward) {
        sessionFactory.getCurrentSession().delete(ward);

    }
}
