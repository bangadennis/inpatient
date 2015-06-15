package org.openmrs.module.inpatient;

import org.openmrs.BaseOpenmrsObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by banga on 5/26/15.
 */
public class Ward  extends BaseOpenmrsObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer wardId;

    private String wardName;

    private String speciality;

    private String category;

    private Integer capacity;

    private Set<Admission>admissions;

    //change and creation meta-data
    private String  createdBy;

    private Date dateCreated;

    private String changedBy;

    private Date dateChanged;



    @Override
    public Integer getId() {
        return getWardId();
    }
    @Override
    public void setId(Integer id) {
        setWardId(id);
    }


    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public Set<Admission> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(Set<Admission> admissions) {
        this.admissions = admissions;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    //method to get Available Ward Capacity

    public int getAvailableWardCapacity(){

        Integer countAdmission=0;
        for(Admission adm:this.admissions){
            if(adm.getDischarge()==null)
            {
                countAdmission=countAdmission+1;
            }
        }
        int availableCapacity=this.capacity-countAdmission;
        return availableCapacity;
    }

}
