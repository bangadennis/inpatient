package org.openmrs.module.inpatient.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.inpatient.Ward;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by banga on 5/26/15.
 */
@Transactional
public interface WardService extends OpenmrsService {

    //list all wards
    @Transactional(readOnly = true)
    List<Ward> getAllWards();
    //gets a ward
    @Transactional(readOnly = true)
    Ward getWard(Integer wardId);
    //Saves a ward
    Ward saveWard(Ward ward);
    //deletes a ward
    void purgeWard(Ward ward);

}
