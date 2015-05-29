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


//	@RequestMapping(value = "/module/inpatient/listwards.form", method = RequestMethod.GET)
//	public void listWards(ModelMap model) {
//		WardService wardService = Context.getService(WardService.class);
//		model.addAttribute("ward", wardService.getAllWards());
//
//	}
//
//	@RequestMapping(value = "/module/inpatient/addward.form", method = RequestMethod.GET)
//	public void wardForm(ModelMap model) {
//		Ward ward=new Ward();
//		model.addAttribute("ward",ward);
//
//	}
//	@RequestMapping(value = "/module/inpatient/saveWard.form", method=RequestMethod.POST)
//	public String saveWardForm(WebRequest request, HttpSession httpSession, ModelMap model,
//							 @RequestParam(required = false, value = "action") String action,
//							 @ModelAttribute("ward") Ward ward, BindingResult errors)
//	{
//
//		WardService wardService = Context.getService(WardService.class);
//
//
//		if (!Context.isAuthenticated()) {
//			errors.reject("ward.auth.required");
//
//		} else
//		{
//
//			try {
//				wardService.saveWard(ward);
//				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Added Successfully");
//				return "redirect:addward.form";
//
//			}
//				catch (Exception ex) {
//
//				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Addition was unsuccessfully");
//			}
//
//		}
//
//		return "redirect:addward.form";
//	}
//
//	@RequestMapping(value = "/module/inpatient/savePatient.form", method=RequestMethod.POST)
//	public String savePatientForm(WebRequest request, HttpSession httpSession, ModelMap model,
//							   @RequestParam(required = false, value = "action") String action,
//							   @ModelAttribute("inpatient") Inpatient inpatient, BindingResult errors)
//	{
//
//		InpatientService inpatientService = Context.getService(InpatientService.class);
//
//
//		if (!Context.isAuthenticated()) {
//			errors.reject("inpatient.auth.required");
//
//		} else
//		{
//
//			try {
//				inpatientService.savePatient(inpatient);
//				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Added Successfully");
//				return "redirect:admission.form";
//
//			}
//			catch (Exception ex) {
//
//				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Addition was unsuccessfully");
//			}
//
//		}
//
//		return "redirect:admission.form";
//	}

}
