package org.openmrs.module.inpatient.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.inpatient.InpatientEncounter;
import org.openmrs.module.inpatient.api.InpatientEncounterService;
import org.openmrs.module.inpatient.api.db.InpatientEncounterDAO;

import java.util.List;

/**
 * Created by banga on 6/16/15.
 */
public class InpatientEncounterServiceImpl extends BaseOpenmrsService implements InpatientEncounterService {


    protected final Log log = LogFactory.getLog(this.getClass());

    private InpatientEncounterDAO dao;

    /**
     * @param dao the dao to set
     */
    public void setDao(InpatientEncounterDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the dao
     */
    public InpatientEncounterDAO getDao() {
        return dao;
    }


    @Override
    public List<InpatientEncounter> getAllInpatientEncounters() {
        return dao.getAllInpatientEncounters();
    }

    @Override
    public InpatientEncounter getInpatientEncounter(Integer encounterId) {
        return dao.getInpatientEncounter(encounterId);
    }

    @Override
    public InpatientEncounter saveInpatientEncounter(InpatientEncounter encounter) {
        return dao.saveInpatientEncounter(encounter);
    }

    @Override
    public void purgeInpatientEncounter(InpatientEncounter encounter) {
        dao.purgeInpatientEncounter(encounter);

    }
}
