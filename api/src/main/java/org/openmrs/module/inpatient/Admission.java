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

    private String inpatientId;


    private String admissionDate;

    private String hivStatus;

    private String nutritionStatus;

    private String guardian;

    private String referralFrom;

    private Integer status;

    //Mapping

    private Inpatient inpatient;

    private Discharge discharge;

    private Integer wardId;




    public String getInpatientId() {
        return inpatientId;
    }

    public void setInpatientId(String inpatientId) {
        this.inpatientId = inpatientId;
    }

    public Integer getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(Integer admissionId) {
        this.admissionId = admissionId;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(String hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getNutritionStatus() {
        return nutritionStatus;
    }

    public void setNutritionStatus(String nutritionStatus) {
        this.nutritionStatus = nutritionStatus;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
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
