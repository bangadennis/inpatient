package org.openmrs.module.inpatient.web.controller;

/**
 * Created by banga on 6/9/15.
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
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
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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


    }

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

}

