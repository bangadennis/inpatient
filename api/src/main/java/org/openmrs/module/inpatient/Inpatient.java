/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.inpatient;

import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Patient;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or {@link BaseOpenmrsMetadata}.
 */
public class Inpatient extends BaseOpenmrsObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private Integer outPatientId;

	private String inpatientId;

	private Integer phoneNumber;

	private Patient patient;


	Set<Admission> admissionSet = new HashSet<Admission>(0);


	//Setters and Getters

	public String getInpatientId() {
		return inpatientId;
	}

	public void setInpatientId(String inpatientId) {
		this.inpatientId = inpatientId;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Integer getOutPatientId() {
		return outPatientId;
	}

	public void setOutPatientId(Integer outPatientId) {
		this.outPatientId = outPatientId;
	}

	//patient
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public Set<Admission> getAdmissionSet() {
		return admissionSet;
	}

	public void setAdmissionSet(Set<Admission> admissionSet) {
		this.admissionSet = admissionSet;
	}


	@Override
	public Integer getId() {
		return getOutPatientId();
	}
	
	@Override
	public void setId(Integer id) {
		setOutPatientId(id);
	}
	
}