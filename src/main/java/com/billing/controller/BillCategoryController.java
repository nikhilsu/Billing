package com.billing.controller;


import com.billing.helper.Constants;
import com.billing.helper.Response;
import com.billing.model.BillCategory;
import com.billing.model.CategoryType;
import com.billing.service.BillCategoryService;
import com.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class BillCategoryController extends BaseController {
    private final BillCategoryService billCategoryService;

    @Autowired
    public BillCategoryController(BillCategoryService billCategoryService, UserService userService) {
        super(userService);
        this.billCategoryService = billCategoryService;
    }

    @RequestMapping(value = Constants.Route.BILL_CATEGORY, method = RequestMethod.GET)
    public String getBillCategories(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        Response<List<BillCategory>> allBillCategories = billCategoryService.getAll();
        allBillCategories.data().sort((c1, c2) -> (c1.getName().compareTo(c2.getName())));
        model.addAttribute(Constants.ModelAttributes.RESULT, allBillCategories.data());
        model.addAttribute(Constants.ModelAttributes.IS_ADMIN, currentUserAdmin(session));
        return Constants.RedirectPage.BILL_CATEGORIES;
    }

    @RequestMapping(value = Constants.Route.BILL_CATEGORY, method = RequestMethod.POST)
    public String createBillCategory(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        boolean isAdmin = currentUserAdmin(session);
        model.addAttribute(Constants.ModelAttributes.IS_ADMIN, isAdmin);
        if (isAdmin) {
            String name = request.getParameter("name");
            String costString = request.getParameter("cost");
            String typeString = request.getParameter("type");
            if (anyParameterNullOrEmpty(Arrays.asList(name, costString, typeString))) {
                model.addAttribute(Constants.ModelAttributes.IS_ERROR, true);
                model.addAttribute(Constants.ModelAttributes.TEST_CATEGORY_MESSAGE, "Fill all fields");
                return Constants.RedirectPage.INDEX;
            }
            double cost = Double.valueOf(costString);
            CategoryType type = CategoryType.valueOf(typeString.toUpperCase());
            Response billCategoryCreation = billCategoryService.createBillCategory(name, cost, type);
            if (!billCategoryCreation.isSuccessful()) {
                model.addAttribute(Constants.ModelAttributes.IS_ERROR, true);
                model.addAttribute(Constants.ModelAttributes.TEST_CATEGORY_MESSAGE, billCategoryCreation.errors().get(0));
                return Constants.RedirectPage.INDEX;
            }
            model.addAttribute(Constants.ModelAttributes.IS_ERROR, false);
            model.addAttribute(Constants.ModelAttributes.TEST_CATEGORY_MESSAGE, "Successfully created!");
        }
        return Constants.RedirectPage.INDEX;
    }

    @RequestMapping(value = Constants.Route.UPDATE_BILL_CATEGORY, method = RequestMethod.POST)
    public String updateBillCategory(HttpServletRequest request, HttpSession session, @PathVariable("id") int billCategoryId, RedirectAttributes redirectAttributes) throws Exception {
        if (currentUserAdmin(session)) {
            String name = request.getParameter("name");
            String costString = request.getParameter("cost");
            String typeString = request.getParameter("type").toUpperCase();
            if (anyParameterNullOrEmpty(Arrays.asList(name, costString, typeString))) {
                redirectAttributes.addAttribute(Constants.ModelAttributes.IS_ERROR, true);
                redirectAttributes.addAttribute(Constants.ModelAttributes.MESSAGE, "All fields required");
                return Constants.Route.REDIRECT + Constants.Route.BILL_CATEGORY;
            }
            double cost = Double.valueOf(costString);
            CategoryType type;
            Response billCategoryUpdate = null;
            try {
                type = CategoryType.valueOf(typeString);
                billCategoryUpdate = billCategoryService.updateBillCategory(billCategoryId, name, cost, type);
            } catch (Exception ignored) {
            }
            boolean isError = billCategoryUpdate == null || !billCategoryUpdate.isSuccessful();
            redirectAttributes.addAttribute(Constants.ModelAttributes.IS_ERROR, isError);
            redirectAttributes.addAttribute(Constants.ModelAttributes.MESSAGE, isError ? "Error, try again!" : "Successfully Updated!");
        }
        return Constants.Route.REDIRECT + Constants.Route.BILL_CATEGORY;
    }
}
