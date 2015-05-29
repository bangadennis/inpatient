package org.openmrs.module.inpatient.api.db;

import org.openmrs.module.inpatient.Discharge;

import java.util.List;

/**
 * Created by banga on 5/28/15.
 */
public interface DischargeDAO {

    // get list of All Discharge
    List<Discharge> getAllDischarge();

    //gets a Discharge
    Discharge getDischarge(Integer dischargeId);

    //Saves Discharge
    Discharge saveDischarge(Discharge discharge);

    //deletes a Discharge
    void purgeDischarge(Discharge discharge);
}
