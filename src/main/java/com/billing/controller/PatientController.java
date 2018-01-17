package com.billing.controller;

import com.billing.helper.Constants;
import com.billing.helper.Response;
import com.billing.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = Constants.Route.PATIENT, method = RequestMethod.GET)
    public String getNewPatientForm() {
        return Constants.RedirectPage.NEW_PATIENT_FORM;
    }

    @RequestMapping(value = Constants.Route.PATIENT, method = RequestMethod.POST)
    public String createNewPatient(HttpServletRequest request) {
        String name = request.getParameter("name");
        int age = Integer.valueOf(request.getParameter("age"));
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Response newPatient = patientService.createNewPatient(name, age, phoneNumber, email);

        return newPatient.isSuccessful() ? Constants.RedirectPage.BILL_CREATE_FORM : Constants.RedirectPage.NEW_PATIENT_FORM;
    }
}
