package com.billing.controller;

import com.billing.helper.Constants;
import com.billing.helper.Masker;
import com.billing.helper.Response;
import com.billing.model.Bill;
import com.billing.model.BillCategory;
import com.billing.model.Patient;
import com.billing.service.BillCategoryService;
import com.billing.service.BillService;
import com.billing.service.PatientService;
import com.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class BillController extends BaseController {
    private final BillService billService;
    private PatientService patientService;
    private BillCategoryService billCategoryService;

    @Autowired
    public BillController(BillService billService, PatientService patientService, BillCategoryService billCategoryService, UserService userService) {
        super(userService);
        this.billService = billService;
        this.patientService = patientService;
        this.billCategoryService = billCategoryService;
    }

    @RequestMapping(value = Constants.Route.NEW_BILL, method = RequestMethod.GET)
    public String getNewBillForm(HttpSession session, @RequestParam("patientId") int patientId, Model model) throws Exception {
        Response<Patient> findPatient = patientService.getById(patientId);
        if (findPatient.isSuccessful()) {
            Response<List<BillCategory>> allCategories = billCategoryService.getAll();
            model.addAttribute("billCategories", allCategories.data());
            model.addAttribute("patient", findPatient.data());
            model.addAttribute(Constants.ModelAttributes.IS_ADMIN, currentUserAdmin(session));
            return Constants.RedirectPage.BILL_CREATE_FORM;
        }
        model.addAttribute(Constants.ModelAttributes.MESSAGE, "Patient not found");
        return Constants.RedirectPage.INDEX;
    }

    @RequestMapping(value = Constants.Route.BILL, method = RequestMethod.GET)
    public String getBills(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        if (currentUserAdmin(session)) {
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            if (anyParameterNullOrEmpty(Arrays.asList(startDate, endDate))) {
                model.addAttribute(Constants.ModelAttributes.BILL_DETAILS_MESSAGE, "Fill in both dates!");
                return Constants.Route.REDIRECT + Constants.Route.ROOT;
            }

            Response<List<Bill>> byDateRange = billService.getByDateRange(startDate, endDate);
            if (!byDateRange.isSuccessful() || byDateRange.data().isEmpty()) {
                model.addAttribute(Constants.ModelAttributes.MESSAGE, "No bills found for this range");
            }
            else {
                List<Bill> bills = byDateRange.data();
                Map<String, Bill> billsByMaskedId = new HashMap<>();
                for (Bill bill : bills) {
                    billsByMaskedId.put(Masker.maskDbId(bill.getId()), bill);
                }
                model.addAttribute(Constants.ModelAttributes.RESULT, billsByMaskedId);
            }
            return Constants.RedirectPage.BILL;
        }
        return Constants.RedirectPage.INDEX;
    }


    @RequestMapping(value = Constants.Route.BILL, method = RequestMethod.POST)
    public ModelAndView createNewBill(HttpServletRequest request) throws Exception {
        String[] billCategoryIds = request.getParameterValues("billCategories");
        int patientId = Integer.valueOf(request.getParameter("patientId"));
        List<BillCategory> billCategories = new ArrayList<>();

        for (String billCategoryId : billCategoryIds) {
            billCategories.add(billCategoryService.getById(Integer.valueOf(billCategoryId)).data());
        }
        Response<Patient> patient = patientService.getById(patientId);

        Response<Integer> billCreation = billService.createBill(patient.data(), billCategories);
        if (billCreation.isSuccessful()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("categories", billCategories);
            map.put("patient", patient.data());
            map.put("billId", billCreation.data());
            return new ModelAndView("pdfRevenueSummary", map);
        }
        return new ModelAndView(Constants.Route.REDIRECT + Constants.Route.ROOT);
    }

    @RequestMapping(value = Constants.Route.VIEW_BILL_PDF)
    public ModelAndView viewBillPdf(@PathVariable("id") int billId) throws Exception {
        Response<Bill> billById = billService.getById(billId);

        if (!billById.isSuccessful()) {
            return new ModelAndView(Constants.Route.REDIRECT + Constants.Route.ROOT);
        }

        Bill bill = billById.data();
        HashMap<String, Object> map = new HashMap<>();
        map.put("categories", bill.getBillCategories());
        map.put("patient", bill.getPatient());
        map.put("billId", bill.getId());
        return new ModelAndView("pdfRevenueSummary", map);
    }
}
