package org.openmrs.module.inpatient;

import org.openmrs.BaseOpenmrsObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by banga on 5/28/15.
 */
public class Discharge extends BaseOpenmrsObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer dischargeId;

    private Date dischargeDate;

    private String diagnosis;

    private String treatment;

    private String outcome;

    private String causeOfDeath;

    private String referralTo;

    private String remarks;

    private Admission admission;



    public Integer getDischargeId() {
        return dischargeId;
    }

    public void setDischargeId(Integer dischargeId) {
        this.dischargeId = dischargeId;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReferralTo() {
        return referralTo;
    }

    public void setReferralTo(String referralTo) {
        this.referralTo = referralTo;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }


    public Admission getAdmission() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }

    @Override
    public Integer getId() {
        return getDischargeId();
    }

    @Override
    public void setId(Integer id) {
        setDischargeId(id);
    }


}