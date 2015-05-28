package org.openmrs.module.inpatient.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.inpatient.Admission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by banga on 5/28/15.
 */
@Transactional
public interface AdmissionService extends OpenmrsService {

    // get list of All admission
    @Transactional(readOnly = true)
    List<Admission> getAllAdmission();
    //gets an admission
    @Transactional(readOnly = true)
    Admission getAdmission(Integer admissionId);

    //Saves admission
    Admission saveAdmission(Admission admission);

    //deletes an admission
    void purgeAdmission(Admission admission);
}
