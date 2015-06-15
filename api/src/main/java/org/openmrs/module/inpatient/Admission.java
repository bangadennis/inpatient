package org.openmrs.module.inpatient;

import org.openmrs.BaseOpenmrsObject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by banga on 5/28/15.
 */
public class Admission extends BaseOpenmrsObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer admissionId;

    private Date admissionDate;

    private Integer hivStatus;

    private Integer hivIntervention;

    private Integer nutritionStatus;

    private String guardian;

    private String referralFrom;


    //added fields
    private String  createdBy;

    private Date dateCreated;

    private String changedBy;

    private Date dateChanged;


    //Mapping

    private Inpatient inpatient;

    private Discharge discharge;

    private Ward ward;

//    Getters and setters
    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Integer getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(Integer hivStatus) {
        this.hivStatus = hivStatus;
    }

    public Integer getHivIntervention() {
        return hivIntervention;
    }

    public void setHivIntervention(Integer hivIntervention) {
        this.hivIntervention = hivIntervention;
    }

    public Integer getNutritionStatus() {
        return nutritionStatus;
    }

    public void setNutritionStatus(Integer nutritionStatus) {
        this.nutritionStatus = nutritionStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public Date getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public Integer getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(Integer admissionId) {
        this.admissionId = admissionId;
    }


    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public String getReferralFrom() {
        return referralFrom;
    }

    public void setReferralFrom(String referralFrom) {
        this.referralFrom = referralFrom;
    }

    //Mappings Getters and Setters
    public Inpatient getInpatient() {
        return inpatient;
    }

    public void setInpatient(Inpatient inpatient) {
        this.inpatient = inpatient;
    }

    public Discharge getDischarge() {
        return discharge;
    }

    public void setDischarge(Discharge discharge) {
        this.discharge = discharge;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    @Override
    public Integer getId() {
        return getAdmissionId();
    }

    @Override
    public void setId(Integer id) {
        setAdmissionId(id);
    }



}
