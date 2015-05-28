package org.openmrs.module.inpatient.api.db;

import org.openmrs.module.inpatient.Inpatient;

import java.util.List;

/**
 * Created by banga on 5/26/15.
 */
public interface InpatientDAO {

    List<Inpatient> getAllInpatient();
    //gets an inpatient
    Inpatient getInpatient(Integer inpatientId);
    //Saves a Inpatient Identifiers
    Inpatient saveInpatient(Inpatient inpatient);
    //deletes an inpatient record
    void purgeInpatient(Inpatient inpatient);

}
