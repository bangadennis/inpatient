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
package org.openmrs.module.inpatient.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.inpatient.Admission;
import org.openmrs.module.inpatient.Discharge;
import org.openmrs.module.inpatient.Inpatient;
import org.openmrs.module.inpatient.Ward;
import org.openmrs.module.inpatient.api.AdmissionService;
import org.openmrs.module.inpatient.api.InpatientService;
import org.openmrs.module.inpatient.api.WardService;
import org.openmrs.validator.PatientIdentifierValidator;q

import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The main controller.
 * @author banga
 */
@Controller
public class  InpatientManageController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/module/inpatient/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {

		model.addAttribute("user", Context.getAuthenticatedUser());
	}

	//Listing outpatients
	@RequestMapping(value = "/module/inpatient/findPatient.form", method = RequestMethod.GET)
	public void findPatient(ModelMap model) {
		PatientService patientService=Context.getPatientService();
		List<Patient> patientList=patientService.getAllPatients();

		model.addAttribute("patientList", patientList);

	}

	//listing Inpatients
	@RequestMapping(value = "/module/inpatient/inpatient.form", method = RequestMethod.GET)
	public void inpatientForm(ModelMap model, @RequestParam(value ="id", required = true)Integer patientId) {

		model.addAttribute("patientId", patientId);

	}




	//Save Inpatient
	@RequestMapping(value = "/module/inpatient/saveInpatient.form", method = RequestMethod.POST)
	public String saveInpatient(ModelMap model,WebRequest request, HttpSession httpSession,
							  @RequestParam(required = true, value = "outpatient_id") Integer patientId,
							  @RequestParam(required = true, value = "inpatient_id") String inpatientId,
							  @RequestParam(required = true, value = "phone_number") Integer phoneNumber)
	{
		InpatientService inpatientService=Context.getService(InpatientService.class);

		try{

			Inpatient inpatient=new Inpatient();
			PatientService patientService=Context.getPatientService();
			Patient patient=patientService.getPatient(patientId);
			//Saving the details

			inpatient.setOutPatientId(patientId);
			inpatient.setInpatientId(inpatientId);
			inpatient.setPhoneNumber(phoneNumber);
			inpatient.setPatient(patient);
			//save inpatient
			inpatientService.saveInpatient(inpatient);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Added Inpatient details Successfully");

		}
		catch (Exception ex)
		{
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Error adding Inpatient");
			return "redirect:listInpatient.form";
		}

		return "redirect:listInpatient.form";

	}


	//listing Inpatients
	@RequestMapping(value = "/module/inpatient/listInpatient.form", method = RequestMethod.GET)
	public void listInpatient(ModelMap model) {
		InpatientService inpatientService=Context.getService(InpatientService.class);
		List<Inpatient>inpatientList=inpatientService.getAllInpatient();

		model.addAttribute("inpatientList", inpatientList);

	}


	//Display Admission Form
	@RequestMapping(value = "/module/inpatient/admission.form", method = RequestMethod.GET)
	public void admissionForm(ModelMap model,
							  @RequestParam(value = "id", required = true)String inpatientId) {

		model.addAttribute("inpatientId", inpatientId);


	}

	//Save Admission Form
	@RequestMapping(value = "/module/inpatient/saveAdmission.form", method = RequestMethod.POST)
	public String saveAdmission(ModelMap model,HttpSession httpSession,WebRequest webRequest,
							  @RequestParam(value = "inpatient_id", required = true)String inpatientId,
							  @RequestParam(value = "admission_date", required = true)String admissionDate,
							  @RequestParam(value = "hiv_status", required = true)String hivStatus,
							  @RequestParam(value = "nutrition_status", required = true)String nutritionStatus,
							  @RequestParam(value = "guardian", required = true)String guardian,
							  @RequestParam(value = "referral_from", required = true)String referralFrom,
							  @RequestParam(value = "status", required = true)Integer status,
								@RequestParam(value = "ward_id", required = true)Integer wardId){

		AdmissionService admissionService=Context.getService(AdmissionService.class);
		InpatientService inpatientService=Context.getService(InpatientService.class);

		try{

			Admission admission=new Admission();
			Inpatient inpatient=inpatientService.getInpatientbyIdentifier(inpatientId);
			admission.setAdmissionDate(admissionDate);
			admission.setHivStatus(hivStatus);
			admission.setNutritionStatus(nutritionStatus);
			admission.setGuardian(guardian);
			admission.setReferralFrom(referralFrom);
			admission.setStatus(status);
			admission.setWardId(wardId);
			admission.setInpatient(inpatient);

			Boolean addAdmission=true;
			Set<Admission> admissionSet=inpatient.getAdmissions();
			if(admissionSet!=null) {
				for(Admission adm: admissionSet)
				{
					Discharge discharge=adm.getDischarge();

					if(discharge==null){
						addAdmission=false;
						break;
					}

				}
			}


			if(addAdmission) {
				admissionService.saveAdmission(admission);
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Added Admission details Successfully");
			}
			else
			{
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Patient not discharged from an admission");
			}

		}
		catch (Exception ex)
		{
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Failed to save Admission details");
			return "redirect:listInpatient.form";

		}

		return "redirect:listInpatient.form";

	}






}
