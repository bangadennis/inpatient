package org.openmrs.module.inpatient;

import org.openmrs.BaseOpenmrsObject;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by banga on 5/26/15.
 */
public class Ward  extends BaseOpenmrsObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer wardId;

    private String wardName;

    private String speciality;

    private String description;

    private Integer capacity;

    private Set<Admission>admissions;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
