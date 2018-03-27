package com.billing.controller;

import com.billing.helper.Constants;
import com.billing.helper.Response;
import com.billing.model.Patient;
import com.billing.service.PatientService;
import com.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class PatientController extends BaseController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService, UserService userService) {
        super(userService);
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
        String phoneNumber = request.getParameter("phoneNumber");
        String ageString = request.getParameter("age");
        String email = request.getParameter("email");
        if (anyParameterNullOrEmpty(Arrays.asList(name, phoneNumber, ageString))) {
            model.addAttribute(Constants.ModelAttributes.IS_ERROR, true);
            model.addAttribute(Constants.ModelAttributes.MESSAGE, "Fill all fields");
            return Constants.RedirectPage.NEW_PATIENT_FORM;
        }
        int age = Integer.valueOf(request.getParameter("age"));

        Response<Integer> newPatient = patientService.createNewPatient(name, age, phoneNumber, email);
        if (newPatient.isSuccessful())
            return Constants.Route.REDIRECT + Constants.Route.NEW_BILL(newPatient.data());

        model.addAttribute(Constants.ModelAttributes.IS_ERROR, false);
        model.addAttribute(Constants.ModelAttributes.MESSAGE, "Cannot create new patient, try again!");
        return Constants.RedirectPage.NEW_PATIENT_FORM;
    }
}
