package org.openmrs.module.inpatient;

import org.openmrs.Encounter;

import java.io.Serializable;

/**
 * Created by banga on 6/16/15.
 */
public class InpatientEncounter extends Encounter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer inpatientEncounterId;

    private Admission admission;

    public Admission getAdmission() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }


    public Integer getInpatientEncounterId() {
        return inpatientEncounterId;
    }

    public void setInpatientEncounterId(Integer inpatientEncounterId) {
        this.inpatientEncounterId = inpatientEncounterId;
    }

    @Override
    public Integer getId() {
        return getInpatientEncounterId();
    }
    @Override
    public void setId(Integer id) {
        setInpatientEncounterId(id);
    }
}
