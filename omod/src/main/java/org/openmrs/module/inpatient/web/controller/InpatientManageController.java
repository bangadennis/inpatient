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
import org.openmrs.api.context.Context;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.inpatient.Admission;
import org.openmrs.module.inpatient.Ward;
import org.openmrs.module.inpatient.api.AdmissionService;
import org.openmrs.module.inpatient.api.WardService;
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
import org.openmrs.api.PatientService;
import org.openmrs.Patient;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import java.util.List;
import org.openmrs.module.inpatient.api.InpatientService;
import org.openmrs.module.inpatient.Inpatient;
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

	@RequestMapping(value = "/module/inpatient/listPatient.form", method = RequestMethod.GET)
	public void listPatient(ModelMap model) {
		List<Patient> patientList=Context.getPatientService().getAllPatients();
		model.addAttribute("patientList", patientList);

	}
	@RequestMapping(value = "/module/inpatient/addInpatient.form", method = RequestMethod.GET)
	public void addInpatient(ModelMap model, @RequestParam(required = true, value = "patientId") Integer patientId){
		Inpatient inpatient=new Inpatient();
		model.addAttribute("inpatient",inpatient);
		model.addAttribute("patientId", patientId);
	}

	//Save Inpatient
	@RequestMapping(value = "/module/inpatient/addInpatient.form", method = RequestMethod.POST)
	public String saveInpatient(ModelMap model,WebRequest request, HttpSession httpSession,
								@RequestParam(required = true, value = "patientId") Integer patientId,
								@RequestParam(required = true, value = "inpatientId") String inpatientId,
								@RequestParam(required = true, value = "phoneNumber") Integer phoneNumber,
								@RequestParam(value = "admission_date", required = true)String admissionDate,
								@RequestParam(value = "hiv_status", required = true)String hivStatus,
								@RequestParam(value = "nutrition_status", required = true)String nutritionStatus,
								@RequestParam(value = "guardian", required = true)String guardian,
								@RequestParam(value = "referral_from", required = true)String referralFrom,
								@RequestParam(value = "status", required = true)Integer status,
								@RequestParam(value = "ward_id", required = true)Integer wardId)
	{
		InpatientService inpatientService = Context.getService(InpatientService.class);
		AdmissionService admissionService=Context.getService(AdmissionService.class);
		WardService wardService=Context.getService(WardService.class);

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
			//we save admission details
			Ward ward=wardService.getWard(wardId);
			inpatient = inpatientService.getInpatientbyIdentifier(inpatientId);

			Admission admission=new Admission();
			admission.setAdmissionDate(admissionDate);
			admission.setHivStatus(hivStatus);
			admission.setNutritionStatus(nutritionStatus);
			admission.setGuardian(guardian);
			admission.setReferralFrom(referralFrom);
			admission.setStatus(status);

			if(ward.getAvailableWardCapacity()>0) {
				admission.setWard(ward);
				admission.setInpatient(inpatient);

				Boolean addAdmission = true;
				Set<Admission> admissionSet = inpatient.getAdmissions();
				if (admissionSet != null) {
					for (Admission adm : admissionSet) {
						Discharge discharge = adm.getDischarge();

						if (discharge == null) {
							addAdmission = false;
							break;
						}

					}
				}


				if (addAdmission) {

					admissionService.saveAdmission(admission);

					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Added Admission details Successfully");
				} else {
					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Patient not discharged from an admission");
				}
			}
			else
			{
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Selected Ward is full");
				return "redirect:addInpatient.form";

			}

			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Added Inpatient details Successfully");


		}
		catch (Exception ex)
		{
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Error adding Inpatient");
			return "redirect:addInpatient.form";
		}

		return "redirect:addInpatient.form";

	}
}
	//list admissions
	@RequestMapping(value = "/module/inpatient/listadmission.form", method = RequestMethod.GET)
	public void listAdmission(ModelMap model) {
		AdmissionService admissionService=Context.getService(AdmissionService.class);
		List<Admission> admissionList=admissionService.getAllAdmission();
		List<Admission> admissions=new ArrayList<Admission>();

		for(Admission adm:admissionList)
		{
			Discharge discharge=adm.getDischarge();
			if(discharge==null){
				admissions.add(adm);
			}

		}

		model.addAttribute("admissionList", admissions);

	}

	//View admission
	@RequestMapping(value = "/module/inpatient/viewAdmission.form", method = RequestMethod.GET)
	public void viewAdmission(ModelMap model,
							  @RequestParam(value ="id", required = true)Integer admissionId) {


		AdmissionService admissionService=Context.getService(AdmissionService.class);
		Admission admission=admissionService.getAdmission(admissionId);
		model.addAttribute("admission", admission);

	}

	//method for deleting  inpatient
	@RequestMapping(value = "/module/inpatient/deleteInpatient.form", method=RequestMethod.GET)
	public String  deleteInpatient(ModelMap model, WebRequest webRequest, HttpSession httpSession,
								   @RequestParam(value = "id", required = true) Integer outpatientId) {
		try {
			InpatientService inpatientService=Context.getService(InpatientService.class);
			Inpatient inpatient=inpatientService.getInpatient(outpatientId);
			inpatientService.purgeInpatient(inpatient);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Deleted Successfully");
			return "redirect:listInpatient.form";
		}
		catch (Exception ex)
		{
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Deletion failed");
			return "redirect:listInpatient.form";

		}

		//return "redirect:manage.form";
	}






	//Display Discharge Form
	@RequestMapping(value = "/module/inpatient/discharge.form", method = RequestMethod.GET)
	public void dischargeForm(ModelMap model,
							  @RequestParam(value = "id", required = true)Integer admissionId) {

		model.addAttribute("admissionId", admissionId);


	}

	//Save Discharge Form
	@RequestMapping(value = "/module/inpatient/saveDischarge.form", method = RequestMethod.POST)
	public String saveDischarge(ModelMap model,HttpSession httpSession,WebRequest webRequest,
								@RequestParam(value = "discharge_id", required = true)Integer dischargeId,
								@RequestParam(value = "discharge_date", required = true)String dischargeDate,
								@RequestParam(value = "treatment", required = true)String treatment,
								@RequestParam(value = "diagnosis", required = true)String diagnosis,
								@RequestParam(value = "outcome", required = true)String outcome,
								@RequestParam(value = "referral_to", required = false)String referralTo,
								@RequestParam(value = "remarks", required = true)String remarks,
								@RequestParam(value = "causeofdeath", required = false)String causeofdeath){

		DischargeService dischargeService=Context.getService(DischargeService.class);
		AdmissionService admissionService=Context.getService(AdmissionService.class);

		try{

			Discharge discharge=new Discharge();
			Admission admission=admissionService.getAdmission(dischargeId);

			discharge.setDischargeId(dischargeId);
			discharge.setDischargeDate(dischargeDate);
			discharge.setTreatment(treatment);
			discharge.setDiagnosis(diagnosis);
			discharge.setOutcome(outcome);
			discharge.setReferralTo(referralTo);
			discharge.setRemarks(remarks);
			discharge.setCauseOfDeath(causeofdeath);
			discharge.setAdmission(admission);

			dischargeService.saveDischarge(discharge);

			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Discharge was Successfully");

		}
		catch (Exception ex)
		{
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Failed to Discharge");
			return "redirect:listadmission.form";

		}

		return "redirect:listadmission.form";

	}





}