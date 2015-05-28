package org.openmrs.module.inpatient.api.db;

import org.openmrs.module.inpatient.Admission;

import java.util.List;

/**
 * Created by banga on 5/28/15.
 */
public interface AdmissionDAO {

    // get list of All admission

    List<Admission> getAllAdmission();
    //gets an admission
    Admission getAdmission(Integer admissionId);

    //Saves admission
    Admission saveAdmission(Admission admission);

    //deletes an admission
    void purgeAdmission(Admission admission);
}
