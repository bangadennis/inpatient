package org.openmrs.module.inpatient.web.controller;

/**
 * Created by banga on 6/9/15.
 */
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.*;
import org.openmrs.api.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.inpatient.*;
import org.openmrs.module.inpatient.api.AdmissionService;
import org.openmrs.module.inpatient.api.InpatientEncounterService;
import org.openmrs.module.inpatient.api.InpatientService;
import org.openmrs.module.inpatient.api.WardService;
import org.openmrs.module.web.extension.provider.Link;
import org.openmrs.util.MetadataComparator;
import org.openmrs.util.OpenmrsConstants;
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
        //Services
        InpatientService inpatientService=Context.getService(InpatientService.class);
        EncounterService encounterService=Context.getEncounterService();
        AdmissionService admissionService=Context.getService(AdmissionService.class);


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
                if(ward.getCategory().equals("Male"))
                {
                    wardList.add(ward);
                }

                if(ward.getCategory().equals("Special"))
                {
                    wardList.add(ward);
                }

                if(ward.getCategory().equals("Minor"))
                {
                    wardList.add(ward);
                }

            }

            if(gender.equals("F"))
            {
                if(ward.getCategory().equals("Female"))
                {
                    wardList.add(ward);
                }
                if(ward.getCategory().equals("Special"))
                {
                    wardList.add(ward);
                }
                if(ward.getCategory().equals("Minor"))
                {
                    wardList.add(ward);
                }

            }
        }


        map.put("wards", wardList);

        //Admissions
        Set<Admission> admissionList=inpatient.getAdmissions();
        List<Admission> admissions=null;
        Admission admission=null;
        if(admissionList.size()!=0)
        {
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

        }
        Set<InpatientEncounter>admissionSet=null;
        List<Location> locationList=null;
        List<EncounterType>encounterTypeList=null;
        Set<InpatientEncounter>encounterList=null;
        String patientIdentifier=null;

        if(admission!=null)
        {
            admissionSet=admission.getEncounters();
            encounterList=admission.getEncounters();
        }

        try {
            locationList=Context.getLocationService().getAllLocations();
            encounterTypeList=Context.getEncounterService().getAllEncounterTypes();
            patientIdentifier=inpatient.getPatient().getPatientIdentifier().toString();

        }
        catch (ObjectRetrievalFailureException ex) {
            log.warn("Error retrieving objects");
        }

        //admission and admission list
        map.put("admission", admission);
        map.put("admissionList", admissions);

        //Location details
        map.put("locationList", locationList);
        //Encounter Types
        map.put("encounterTypeList", encounterTypeList);
        //Listing Encounters
        map.put("encounterList", encounterList);
        //openmrs patient identifier
        map.put("patientIdentifier", patientIdentifier);

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
                                @RequestParam(value = "admission_id", required=true)Integer admissionId,
                              @RequestParam(value = "patient_id", required=true)Integer patientId,
                              @RequestParam(value = "encounter_date", required=true)Date encounterDate,
                              @RequestParam(value = "encounter_type", required=true)Integer encounterId,
                              @RequestParam(value = "location", required=true)Integer locationId) {

       try {

           EncounterService encounterService=Context.getEncounterService();
           InpatientService inpatientService=Context.getService(InpatientService.class);
           AdmissionService admissionService=Context.getService(AdmissionService.class);
           InpatientEncounterService inpatientEncounterService=Context.getService(InpatientEncounterService.class);
           Inpatient inpatient=inpatientService.getInpatient(patientId);

           Admission admission=admissionService.getAdmission(admissionId);
           InpatientEncounter inpatientEncounter=new InpatientEncounter();

           inpatientEncounter.setPatient(inpatient.getPatient());
           inpatientEncounter.setLocation(Context.getLocationService().getLocation(locationId));
           inpatientEncounter.setEncounterType(encounterService.getEncounterType(encounterId));
           inpatientEncounter.setEncounterDatetime(new Date());
           inpatientEncounter.setAdmission(admission);

           //saving Inpatient Encounter
           inpatientEncounterService.saveInpatientEncounter(inpatientEncounter);


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

        InpatientEncounterService encounterService=Context.getService(InpatientEncounterService.class);

        Obs obs=new Obs();
        model.addAttribute("encounterId",encounterId);
        model.addAttribute("obs",obs);
        try{
            InpatientEncounter encounter=encounterService.getInpatientEncounter(encounterId);
            Set<Obs>obsList=encounter.getAllObs();
            model.addAttribute("obsList", obsList);
        }
        catch (Exception ex)
        {
            log.warn("Unable to retrieve encounter");
        }

    }

    //saveObs
    @RequestMapping(value = "/module/inpatient/saveObs.form", method=RequestMethod.POST)
    public String saveObsForm(WebRequest request, HttpSession httpSession, ModelMap model,
                               @RequestParam(required = true, value = "id") int encounterId,
                               @ModelAttribute("obs") Obs obs, BindingResult errors)
    {
        InpatientEncounterService inpatientEncounterService=Context.getService(InpatientEncounterService.class);
        ObsService obsService = Context.getObsService();

        InpatientEncounter encounter=inpatientEncounterService.getInpatientEncounter(encounterId);
        int patientId=encounter.getPatientId();
        try {

            int personId=encounter.getPatient().getPersonId();

            obs.setDateCreated(new Date());
            obs.setLocation(encounter.getLocation());
            obs.setCreator(Context.getAuthenticatedUser().getCreator());
            obs.setPerson(Context.getPersonService().getPerson(personId));
            obs.setEncounter(encounter);
            String reason=new String("first Save");
            obsService.saveObs(obs, reason);

            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Added Observation");

        }
        catch (Exception ex)
        {
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Error adding Observation");
            return "redirect:obs.form?id="+encounterId;

        }

        return "redirect:obs.form?id="+encounterId;
    }



}

