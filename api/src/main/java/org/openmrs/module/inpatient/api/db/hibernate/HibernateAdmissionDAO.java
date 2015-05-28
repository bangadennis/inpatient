package org.openmrs.module.inpatient.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.inpatient.Admission;
import org.openmrs.module.inpatient.api.db.AdmissionDAO;

import java.util.List;

/**
 * Created by banga on 5/28/15.
 */
public class HibernateAdmissionDAO implements AdmissionDAO {
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
    public List<Admission> getAllAdmission() {
        return sessionFactory.getCurrentSession().createCriteria(Admission.class).list();
    }

    @Override
    public Admission getAdmission(Integer admissionId) {
        return (Admission) sessionFactory.getCurrentSession().get(Admission.class, admissionId);
    }

    @Override
    public Admission saveAdmission(Admission admission) {
         sessionFactory.getCurrentSession().save(admission);
        return admission;
    }

    @Override
    public void purgeAdmission(Admission admission) {
        sessionFactory.getCurrentSession().delete(admission);

    }
}
