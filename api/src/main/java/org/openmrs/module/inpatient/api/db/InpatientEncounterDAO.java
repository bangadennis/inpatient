package org.openmrs.module.inpatient.api.db;

import org.openmrs.module.inpatient.InpatientEncounter;

import java.util.List;

/**
 * Created by banga on 6/16/15.
 */
public interface InpatientEncounterDAO {

    List<InpatientEncounter> getAllInpatientEncounters();

    InpatientEncounter getInpatientEncounter(Integer encounterId);

    InpatientEncounter saveInpatientEncounter(InpatientEncounter encounter);

    void purgeInpatientEncounter( InpatientEncounter encounter);
}
