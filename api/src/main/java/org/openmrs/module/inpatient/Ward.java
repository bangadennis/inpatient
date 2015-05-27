package org.openmrs.module.inpatient;

import org.openmrs.BaseOpenmrsObject;

import java.io.Serializable;

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

}
