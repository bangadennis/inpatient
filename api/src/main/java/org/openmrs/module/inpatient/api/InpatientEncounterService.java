package org.openmrs.module.inpatient.api;

import org.openmrs.api.EncounterService;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.inpatient.InpatientEncounter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by banga on 6/16/15.
 */
@Transactional
public interface InpatientEncounterService extends OpenmrsService {


    @Transactional(readOnly = true)
    List<InpatientEncounter> getAllInpatientEncounters();

    @Transactional(readOnly = true)
    InpatientEncounter getInpatientEncounter(Integer encounterId);

    InpatientEncounter saveInpatientEncounter(InpatientEncounter encounter);

    void purgeInpatientEncounter( InpatientEncounter encounter);
}
