package com.billing.controller;


import com.billing.helper.Constants;
import com.billing.helper.Response;
import com.billing.model.BillCategory;
import com.billing.model.CategoryType;
import com.billing.service.BillCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BillCategoryController {
    private final BillCategoryService billCategoryService;

    @Autowired
    public BillCategoryController(BillCategoryService billCategoryService) {
        this.billCategoryService = billCategoryService;
    }

    @RequestMapping(name = Constants.Route.BILL_CATEGORY, method = RequestMethod.GET)
    public String getBillCategoryForm(Model model) {
        model.addAttribute(Constants.ModelAttributes.UPDATE, false);
        return Constants.RedirectPage.BILL_CATEGORY_FORM;
    }

    @RequestMapping(name = Constants.Route.BILL_CATEGORY, method = RequestMethod.POST)
    public String createBillCategory(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        float cost = Float.valueOf(request.getParameter("cost"));
        CategoryType type = CategoryType.valueOf(request.getParameter("type"));
        Response billCategoryCreation = billCategoryService.createBillCategory(name, cost, type);

        model.addAttribute(Constants.ModelAttributes.MESSAGE, billCategoryCreation.isSuccessful() ? "Success" : "Failed");
        return Constants.RedirectPage.INDEX;
    }

    @RequestMapping(name = Constants.Route.UPDATE_BILL_CATEGORY, method = RequestMethod.GET)
    public String getBillCategoryForm(@PathVariable("id") int id, Model model) throws Exception {
        Response<BillCategory> getBillCategoryById = billCategoryService.getById(id);
        model.addAttribute(Constants.ModelAttributes.UPDATE, true);
        model.addAttribute(Constants.ModelAttributes.RESULT, getBillCategoryById.data());
        return Constants.RedirectPage.BILL_CATEGORY_FORM;
    }

    @RequestMapping(name = Constants.Route.UPDATE_BILL_CATEGORY, method = RequestMethod.POST)
    public String createBillCategory(HttpServletRequest request, @PathVariable("id") int billCategoryId, Model model) throws Exception {
        String name = request.getParameter("name");
        float cost = Float.valueOf(request.getParameter("cost"));
        CategoryType type = CategoryType.valueOf(request.getParameter("type"));
        Response billCategoryUpdation = billCategoryService.updateBillCategory(billCategoryId, name, cost, type);

        model.addAttribute(Constants.ModelAttributes.MESSAGE, billCategoryUpdation.isSuccessful() ? "Success" : "Failed");
        return Constants.RedirectPage.INDEX;
    }
}
