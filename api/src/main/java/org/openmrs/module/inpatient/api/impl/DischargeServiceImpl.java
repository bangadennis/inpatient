package org.openmrs.module.inpatient.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.inpatient.Discharge;
import org.openmrs.module.inpatient.api.DischargeService;
import org.openmrs.module.inpatient.api.db.DischargeDAO;

import java.util.List;

/**
 * Created by banga on 5/28/15.
 */
public class DischargeServiceImpl  extends BaseOpenmrsService implements DischargeService {

    protected final Log log = LogFactory.getLog(this.getClass());

    private DischargeDAO dao;

    /**
     * @param dao the dao to set
     */
    public void setDao(DischargeDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the dao
     */
    public DischargeDAO getDao() {
        return dao;
    }

    @Override
    public List<Discharge> getAllDischarge() {
        return dao.getAllDischarge();
    }

    @Override
    public Discharge getDischarge(Integer dischargeId) {
        return dao.getDischarge(dischargeId);
    }

    @Override
    public Discharge saveDischarge(Discharge discharge) {
        return dao.saveDischarge(discharge);
    }

    @Override
    public void purgeDischarge(Discharge discharge) {
        dao.purgeDischarge(discharge);
    }
}
