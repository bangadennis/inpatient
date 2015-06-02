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
import org.openmrs.module.inpatient.Ward;
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
import org.openmrs.module.inpatient.*;

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
	public void addInpatient(ModelMap model){
		Inpatient inpatient=new Inpatient();
		model.addAttribute("inpatient",inpatient);
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
			return "redirect:listPatient.form";
		}

		return "redirect:listPatient.form";

	}
}
