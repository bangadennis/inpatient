package org.openmrs.module.inpatient.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.inpatient.Admission;
import org.openmrs.module.inpatient.api.AdmissionService;
import org.openmrs.module.inpatient.api.db.AdmissionDAO;

import java.util.List;

/**
 * Created by banga on 5/28/15.
 */
public class AdmissionServiceImpl extends BaseOpenmrsService implements AdmissionService {

    protected final Log log = LogFactory.getLog(this.getClass());

    private AdmissionDAO dao;

    /**
     * @param dao the dao to set
     */
    public void setDao(AdmissionDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the dao
     */
    public AdmissionDAO getDao() {
        return dao;
    }


    @Override
    public List<Admission> getAllAdmission() {
        return dao.getAllAdmission();
    }

    @Override
    public Admission getAdmission(Integer admissionId) {
        return dao.getAdmission(admissionId);
    }

    @Override
    public Admission saveAdmission(Admission admission) {
        return dao.saveAdmission(admission);
    }

    @Override
    public void purgeAdmission(Admission admission) {
        dao.purgeAdmission(admission);

    }
}
