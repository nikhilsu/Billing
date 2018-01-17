package com.billing.controller;

import com.billing.helper.Constants;
import com.billing.helper.Response;
import com.billing.model.BillCategory;
import com.billing.model.Patient;
import com.billing.service.BillCategoryService;
import com.billing.service.BillService;
import com.billing.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BillController {
    private final BillService billService;
    private PatientService patientService;
    private BillCategoryService billCategoryService;

    public BillController(BillService billService, PatientService patientService, BillCategoryService billCategoryService) {
        this.billService = billService;
        this.patientService = patientService;
        this.billCategoryService = billCategoryService;
    }

    @RequestMapping(value = Constants.Route.BILL, method = RequestMethod.GET)
    public String getNewBillForm(@RequestParam("phoneNumber") String phoneNumber, Model model) throws Exception {
        Response<Patient> findPatient = patientService.getByPhoneNumber(phoneNumber);
        if (findPatient.isSuccessful()) {
            Response<List<BillCategory>> allCategories = billCategoryService.getAll();
            model.addAttribute("billCategories", allCategories.data());
            model.addAttribute("patient", findPatient.data());

            return Constants.RedirectPage.BILL_CREATE_FORM;
        }
        model.addAttribute(Constants.ModelAttributes.MESSAGE, "Patient not found");
        return Constants.RedirectPage.INDEX;
    }

    @RequestMapping(value = Constants.Route.BILL, method = RequestMethod.POST)
    public String createNewBill(HttpServletRequest request, Model model) throws Exception {
        String[] billCategoryIds = request.getParameterValues("billCategories");
        int patientId = Integer.valueOf(request.getParameter("patientId"));
        List<BillCategory> billCategories = new ArrayList<>();

        for (String billCategoryId : billCategoryIds) {
            billCategories.add(billCategoryService.getById(Integer.valueOf(billCategoryId)).data());
        }
        Response<Patient> patient = patientService.getById(patientId);

        Response billCreation = billService.createBill(patient.data(), billCategories);
        model.addAttribute(Constants.ModelAttributes.MESSAGE, billCreation.isSuccessful() ? "Success" : "Failed");
        return Constants.RedirectPage.INDEX;
    }
}
