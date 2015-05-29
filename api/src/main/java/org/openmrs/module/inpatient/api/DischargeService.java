package org.openmrs.module.inpatient.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.inpatient.Discharge;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by banga on 5/28/15.
 */
@Transactional
public interface DischargeService extends OpenmrsService {

    // get list of All Discharge
    @Transactional(readOnly = true)
    List<Discharge> getAllDischarge();

    //gets a Discharge
    @Transactional(readOnly = true)
    Discharge getDischarge(Integer dischargeId);

    //Saves Discharge
    Discharge saveDischarge(Discharge discharge);

    //deletes a Discharge
    void purgeDischarge(Discharge discharge);

}
