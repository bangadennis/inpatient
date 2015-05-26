package org.openmrs.module.inpatient.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.inpatient.Ward;
import org.openmrs.module.inpatient.api.WardService;
import org.openmrs.module.inpatient.api.db.WardDAO;

import java.util.List;

/**
 * Created by banga on 5/26/15.
 */
public class WardServiceImpl extends BaseOpenmrsService implements WardService {

    protected final Log log = LogFactory.getLog(this.getClass());

    private WardDAO dao;

    public void setDao(WardDAO dao) {
        this.dao = dao;
    }
    /**
     * @return the dao
     */
    public WardDAO getDao() {
        return dao;
    }


    @Override
    public List<Ward> getAllWards() {
        return dao.getAllWards();
    }

    @Override
    public Ward getWard(Integer wardId) {
        return dao.getWard(wardId);
    }

    @Override
    public Ward saveWard(Ward ward) {
        return dao.saveWard(ward);
    }

    @Override
    public void purgeWard(Ward ward) {
        dao.purgeWard(ward);

    }
}
