
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
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;;
import org.openmrs.api.EncounterService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.inpatient.Admission;
import org.openmrs.module.inpatient.Discharge;
import org.openmrs.module.inpatient.Inpatient;
import org.openmrs.module.inpatient.Ward;
import org.openmrs.module.inpatient.api.AdmissionService;
import org.openmrs.module.inpatient.api.DischargeService;
import org.openmrs.module.inpatient.api.InpatientService;
import org.openmrs.module.inpatient.api.WardService;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
		InpatientService inpatientService=Context.getService(InpatientService.class);

		List<Patient> patientList=patientService.getAllPatients();
		List<Inpatient>inpatientList=inpatientService.getAllInpatient();
		List<Patient>patients=new ArrayList<Patient>();

		Boolean check=true;

		for(Patient patient:patientList)
		{	check=true;

			for(Inpatient inpatient:inpatientList)
			{
				if(patient==inpatient.getPatient())
				{
					check=false;
					break;
				}
			}

			if(check)
			{
				patients.add(patient);
			}

		}

		model.addAttribute("patientList", patients);

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
			EncounterService encounterService=Context.getEncounterService();
			//adding encounter details
			Encounter encounter=new Encounter();
			encounter.setLocation(Context.getLocationService().getDefaultLocation());
			encounter.setPatient(patient);
			encounter.setEncounterDatetime(new Date());
			//Getting Inpatient Registration encounter type
			encounter.setEncounterType(encounterService.getEncounterTypeByUuid("ed30255d-4b6b-4d4a-a951-6e864cc17ecd"));

			//save encounter
			encounterService.saveEncounter(encounter);


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
			return "redirect:inpatient.form?id="+patientId;
		}

		return "redirect:processRequest.form?id="+patientId;

	}


	//listing Inpatients
	@RequestMapping(value = "/module/inpatient/listInpatient.form", method = RequestMethod.GET)
	public void listInpatient(ModelMap model) {
		InpatientService inpatientService=Context.getService(InpatientService.class);
		List<Inpatient>inpatientList=inpatientService.getAllInpatient();

		WardService wardService=Context.getService(WardService.class);

		model.addAttribute("wards", wardService.getAllWards());

		model.addAttribute("inpatientList", inpatientList);

	}


	//Display Admission Form
	@RequestMapping(value = "/module/inpatient/admission.form", method = RequestMethod.GET)
	public void admissionForm(ModelMap model,
							  @RequestParam(value = "id", required = true)String inpatientId) {

		WardService wardService=Context.getService(WardService.class);

		model.addAttribute("wards", wardService.getAllWards());
		model.addAttribute("inpatientId", inpatientId);


	}

	//Save Admission Form
	@RequestMapping(value = "/module/inpatient/saveAdmission.form", method = RequestMethod.POST)
	public String saveAdmission(ModelMap model,HttpSession httpSession,WebRequest webRequest,
							  @RequestParam(value = "inpatient_id", required = true)Integer patientId,
							  @RequestParam(value = "admission_date", required = true)String admissionDate,
							  @RequestParam(value = "hiv_status", required = true)String hivStatus,
							  @RequestParam(value = "nutrition_status", required = true)String nutritionStatus,
							  @RequestParam(value = "guardian", required = true)String guardian,
							  @RequestParam(value = "referral_from", required = true)String referralFrom,
							  @RequestParam(value = "status", required = true)Integer hivIntervention,
								@RequestParam(value = "ward_id", required = true)Integer wardId){

		AdmissionService admissionService=Context.getService(AdmissionService.class);
		InpatientService inpatientService=Context.getService(InpatientService.class);
		WardService wardService=Context.getService(WardService.class);

		try{

			Ward ward=wardService.getWard(wardId);
			Inpatient inpatient=inpatientService.getInpatient(patientId);
			Patient patient=inpatient.getPatient();

			//check if patient is alive
			if(patient.getDead())
			{
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Patient is Dead");
				return "redirect:listInpatient.form";
			}

			//condition for admissionDate
			if(admissionDate==null)
			{
				Date date=new Date();
			}
			Admission admission=new Admission();
			admission.setAdmissionDate(admissionDate);
			admission.setHivStatus(hivStatus);
			admission.setNutritionStatus(nutritionStatus);
			admission.setGuardian(guardian);
			admission.setReferralFrom(referralFrom);
			admission.setStatus(hivIntervention);

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
					//EncounterService
//					EncounterService encounterService=Context.getEncounterService();
//					Encounter encounter=new Encounter();
//					encounter.setLocation(Context.getLocationService().getDefaultLocation());
//					encounter.setPatient(patient);
//					encounter.setEncounterDatetime(new Date());
//					//Getting Inpatient Registration encounter type
//					encounter.setEncounterType(encounterService.getEncounterTypeByUuid("384a59c5-f744-4e1e-8bf2-08de6237b036"));
//					encounterService.saveEncounter(encounter);

					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Added Admission details Successfully");
				} else {
					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Patient not discharged from an admission");
				}
			}
			else
			{
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Selected Ward is full");
				return "redirect:processRequest.form?id="+patientId;

			}

		}
		catch (Exception ex)
		{
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Failed to save Admission details");
			return "redirect:processRequest.form?id="+patientId;

		}


		return "redirect:processRequest.form?id="+patientId;

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


	//list discharge
	@RequestMapping(value = "/module/inpatient/listdischarge.form", method = RequestMethod.GET)
	public void listDischarge(ModelMap model) {
		AdmissionService admissionService=Context.getService(AdmissionService.class);
		List<Admission> admissionList=admissionService.getAllAdmission();
		List<Admission> admissions=new ArrayList<Admission>();

		for(Admission adm:admissionList)
		{
			Discharge discharge=adm.getDischarge();
			if(discharge!=null){
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
								@RequestParam(value = "causeofdeath", required = true)String causeofdeath){

		DischargeService dischargeService=Context.getService(DischargeService.class);
		AdmissionService admissionService=Context.getService(AdmissionService.class);

		Admission admission=admissionService.getAdmission(dischargeId);
		int patientId=admission.getInpatient().getOutPatientId();

		try{

			Discharge discharge=new Discharge();
			PatientService patientService=Context.getPatientService();

			discharge.setDischargeId(dischargeId);
			discharge.setDischargeDate(dischargeDate);
			discharge.setTreatment(treatment);
			discharge.setDiagnosis(diagnosis);
			discharge.setOutcome(outcome);
			discharge.setReferralTo(referralTo);
			discharge.setRemarks(remarks);
			discharge.setCauseOfDeath(causeofdeath);
			discharge.setAdmission(admission);

			if(outcome.equals("D"))
			{
				Patient patient=admission.getInpatient().getPatient();
				patient.setDead(true);
				patient.setDeathDate(new Date());
				Concept concept=Context.getConceptService().getConceptByUuid("e5678f1f-0de8-11e5-b470-a4badbd9b830");
				patient.setCauseOfDeath(concept);
				patientService.savePatient(patient);
			}

			dischargeService.saveDischarge(discharge);

			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Discharge was Successfully");

		}
		catch (Exception ex)
		{
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Failed to Discharge");
			return "redirect:processRequest.form?id="+patientId;

		}

		return "redirect:processRequest.form?id="+patientId;

	}





}
