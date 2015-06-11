package org.openmrs.module.inpatient.web.controller;

/**
 * Created by banga on 6/9/15.
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.*;
import org.openmrs.api.EncounterService;
import org.openmrs.api.LocationService;
import org.openmrs.api.ObsService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.inpatient.Admission;
import org.openmrs.module.inpatient.Discharge;
import org.openmrs.module.inpatient.Inpatient;
import org.openmrs.module.inpatient.Ward;
import org.openmrs.module.inpatient.api.AdmissionService;
import org.openmrs.module.inpatient.api.InpatientService;
import org.openmrs.module.inpatient.api.WardService;
import org.openmrs.module.web.extension.provider.Link;
import org.openmrs.web.WebConstants;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class InpatientDashboardController {
    /** Logger for this class and subclasses */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * render the patient dashboard model and direct to the view
     */
    @RequestMapping(value="/module/inpatient/inpatientDashboardForm.form", method = RequestMethod.GET)
    protected void renderDashboard(@RequestParam(required = true, value = "id") Integer patientId, ModelMap map)
            throws Exception {
        InpatientService inpatientService=Context.getService(InpatientService.class);
        Inpatient inpatient=null;

        try {
            inpatient = inpatientService.getInpatient(patientId);
        }
        catch (ObjectRetrievalFailureException noPatientEx) {
            log.warn("There is no patient with id: '" + patientId + "'", noPatientEx);
        }

        if (inpatient == null) {
//            throw new ServletException("There is no patient with id: '" + patientId + "'");
//            return "redirect:inpatient.form?id="+patientId;
        }

        log.debug("inpatient: '" + inpatient + "'");
        map.put("inpatient", inpatient);
        WardService wardService=Context.getService(WardService.class);

        List<Ward>wards=wardService.getAllWards();
        List<Ward>wardList=new ArrayList<Ward>();
        String gender=inpatient.getPatient().getGender();
        for(Ward ward:wards)
        {
            if(gender.equals("M"))
            {
                if(ward.getDescription().equals("Male"))
                {
                    wardList.add(ward);
                }
                if(ward.getDescription().equals("Special"))
                {
                    wardList.add(ward);
                }
                if(ward.getDescription().equals("Child"))
                {
                    wardList.add(ward);
                }

            }

            if(gender.equals("F"))
            {
                if(ward.getDescription().equals("Female"))
                {
                    wardList.add(ward);
                }
                if(ward.getDescription().equals("Special"))
                {
                    wardList.add(ward);
                }
                if(ward.getDescription().equals("Child"))
                {
                    wardList.add(ward);
                }

            }
        }


        map.put("wards", wardList);
        //Admissions
        AdmissionService admissionService=Context.getService(AdmissionService.class);
        Set<Admission> admissionList=inpatient.getAdmissions();
        List<Admission> admissions=new ArrayList<Admission>();
        Admission admission=null;
        for(Admission adm:admissionList)
        {
            Discharge discharge=adm.getDischarge();
            if(discharge!=null){
                admissions.add(adm);
            }
            if(discharge==null)
            {
                admission=adm;
            }

        }

        map.put("admission", admission);
        map.put("admissionList", admissions);

        //Location details
        List<Location> locationList=Context.getLocationService().getAllLocations();
        map.put("locationList", locationList);

        //Encounter Types
        List<EncounterType>encounterTypeList=Context.getEncounterService().getAllEncounterTypes();
        map.put("encounterTypeList", encounterTypeList);

//        Listing Encounters
        EncounterService encounterService=Context.getEncounterService();
        List<Encounter>encounterList=encounterService.getEncountersByPatient(inpatient.getPatient());
        map.put("encounterList", encounterList);
        map.put("patientIdentifier", inpatient.getPatient().getPatientIdentifier().toString());


    }

    //process Search request
    @RequestMapping(value = "/module/inpatient/processRequest.form", method = RequestMethod.GET)
    public String processRequest(ModelMap model,
                                 @RequestParam(required = true, value = "id") Integer id) {

        InpatientService inpatientService=Context.getService(InpatientService.class);
        Inpatient inpatient=null;

        try {
            inpatient = inpatientService.getInpatient(id);
        }
        catch (ObjectRetrievalFailureException noPatientEx) {
            log.warn("There is no patient with id: '" + id + "'", noPatientEx);
        }

        if (inpatient == null) {
//            throw new ServletException("There is no patient with id: '" + patientId + "'");
            return "redirect:inpatient.form?id="+id;
        }

        return "redirect:inpatientDashboardForm.form?id="+id;
    }


//    Save Encounter
    @RequestMapping(value = "/module/inpatient/saveEncounter.form", method = RequestMethod.POST)
    public String saveEncounter(ModelMap model, WebRequest webRequest, HttpSession httpSession,
                              @RequestParam(value = "patient_id", required=true)Integer patientId,
                              @RequestParam(value = "encounter_date", required=true)Date encounterDate,
                              @RequestParam(value = "encounter_type", required=true)Integer encounterId,
                              @RequestParam(value = "location", required=true)Integer locationId,
                              @RequestParam(value = "provider_role", required=true)String providerRole) {

       try {

           EncounterService encounterService=Context.getEncounterService();
           InpatientService inpatientService=Context.getService(InpatientService.class);
           Inpatient inpatient=inpatientService.getInpatient(patientId);

           Encounter encounter=new Encounter();
           encounter.setPatient(inpatient.getPatient());
           encounter.setLocation(Context.getLocationService().getLocation(locationId));
           encounter.setEncounterDatetime(encounterDate);
           encounter.setEncounterType(encounterService.getEncounterType(encounterId));

           encounterService.saveEncounter(encounter);
           httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Encounter  Added");

       }
        catch (Exception ex)
        {
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Error  Adding Encounter");
            return "redirect:inpatientDashboardForm.form?id="+patientId;

        }

        return "redirect:inpatientDashboardForm.form?id="+patientId;
    }

    @RequestMapping(value = "/module/inpatient/obs.form", method = RequestMethod.GET)
    public void showObsForm(ModelMap model,
                            @RequestParam(value = "id", required = true)Integer encounterId) {

        Obs obs=new Obs();
        model.addAttribute("encounterId",encounterId);
        model.addAttribute("obs",obs);
        try{
            EncounterService encounterService=Context.getEncounterService();
            Encounter encounter=encounterService.getEncounter(encounterId);
            Set<Obs>obsList=encounter.getAllObs();
            model.addAttribute("obsList", obsList);
        }
        catch (Exception ex)
        {

        }

    }

    //saveObs
    @RequestMapping(value = "/module/inpatient/saveObs.form", method=RequestMethod.POST)
    public String saveObsForm(WebRequest request, HttpSession httpSession, ModelMap model,
                               @RequestParam(required = true, value = "id") int encounterId,
                               @ModelAttribute("obs") Obs obs, BindingResult errors)
    {
        Encounter encounter =Context.getEncounterService().getEncounter(encounterId);
        int patientId=encounter.getPatientId();
        try {

            int personId=encounter.getPatient().getPersonId();
            EncounterService encounterService=Context.getEncounterService();
            ObsService obsService=Context.getObsService();

            obs.setDateCreated(new Date());
            obs.setLocation(encounter.getLocation());
            obs.setCreator(Context.getAuthenticatedUser());
            obs.setPerson(Context.getPersonService().getPerson(personId));
            obs.setEncounter(encounter);
            String reason=new String("first Save");
            obsService.saveObs(obs, reason);
//            encounter.addObs(obs);
//            encounterService.saveEncounter(encounter);

            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Added Observation");

        }
        catch (Exception ex)
        {
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Error adding Observation");
            return "redirect:inpatientDashboardForm.form?id="+patientId;

        }

        return "redirect:inpatientDashboardForm.form?id="+patientId;
    }



}

