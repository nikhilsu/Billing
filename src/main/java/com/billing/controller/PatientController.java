package com.billing.controller;

import com.billing.helper.Constants;
import com.billing.helper.Response;
import com.billing.model.Patient;
import com.billing.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = Constants.Route.NEW_PATIENT_FORM, method = RequestMethod.GET)
    public String getNewPatientForm() {
        return Constants.RedirectPage.NEW_PATIENT_FORM;
    }

    @RequestMapping(value = Constants.Route.PATIENT, method = RequestMethod.GET)
    public String getPatientsByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber, Model model) throws Exception {
        Response<List<Patient>> byPhoneNumber = patientService.getByPhoneNumber(phoneNumber);
        if (!byPhoneNumber.isSuccessful() || byPhoneNumber.data().isEmpty()) {
            model.addAttribute(Constants.ModelAttributes.MESSAGE, "Patient not found.");
        }
        else  {
            model.addAttribute(Constants.ModelAttributes.RESULT, byPhoneNumber.data());
        }
        return Constants.RedirectPage.PATIENT;
    }

    @RequestMapping(value = Constants.Route.PATIENT, method = RequestMethod.POST)
    public String createNewPatient(HttpServletRequest request, Model model) throws Exception {
        String name = request.getParameter("name");
        int age = Integer.valueOf(request.getParameter("age"));
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Response<Integer> newPatient = patientService.createNewPatient(name, age, phoneNumber, email);
        if (newPatient.isSuccessful())
            return Constants.Route.REDIRECT + Constants.Route.BILL(newPatient.data());

        model.addAttribute(Constants.ModelAttributes.MESSAGE, "Cannot create new patient");
        return Constants.RedirectPage.NEW_PATIENT_FORM;
    }
}
