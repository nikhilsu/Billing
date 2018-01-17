package com.billing.controller;


import com.billing.helper.Constants;
import com.billing.helper.Response;
import com.billing.model.BillCategory;
import com.billing.model.CategoryType;
import com.billing.model.User;
import com.billing.service.BillCategoryService;
import com.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BillCategoryController {
    private final BillCategoryService billCategoryService;
    private final UserService userService;

    @Autowired
    public BillCategoryController(BillCategoryService billCategoryService, UserService userService) {
        this.billCategoryService = billCategoryService;
        this.userService = userService;
    }

    @RequestMapping(value = Constants.Route.BILL_CATEGORY, method = RequestMethod.GET)
    public String getBillCategories(HttpSession session, Model model) throws Exception {
        Response<List<BillCategory>> allBillCategories = billCategoryService.getAll();
        model.addAttribute(Constants.ModelAttributes.RESULT, allBillCategories.data());
        model.addAttribute(Constants.ModelAttributes.IS_ADMIN, currentUserAdmin(session));
        return Constants.RedirectPage.BILL_CATEGORIES;
    }

    @RequestMapping(value = Constants.Route.BILL_CATEGORY, method = RequestMethod.POST)
    public String createBillCategory(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        if (currentUserAdmin(session)) {
            String name = request.getParameter("name");
            double cost = Double.valueOf(request.getParameter("cost"));
            CategoryType type = CategoryType.valueOf(request.getParameter("type").toUpperCase());
            Response billCategoryCreation = billCategoryService.createBillCategory(name, cost, type);
            model.addAttribute(Constants.ModelAttributes.MESSAGE, billCategoryCreation.isSuccessful() ? "Success" : "Failed");
        }
        return Constants.RedirectPage.INDEX;
    }

    @RequestMapping(value = Constants.Route.UPDATE_BILL_CATEGORY, method = RequestMethod.GET)
    public String getCategoryUpdateForm(HttpSession session, @PathVariable("id") int id, Model model) throws Exception {
        if (currentUserAdmin(session)) {
            Response<BillCategory> getBillCategoryById = billCategoryService.getById(id);
            model.addAttribute(Constants.ModelAttributes.RESULT, getBillCategoryById.data());
            return Constants.RedirectPage.BILL_CATEGORY_UPDATE_FORM;
        }
        return Constants.RedirectPage.INDEX;
    }

    @RequestMapping(value = Constants.Route.UPDATE_BILL_CATEGORY, method = RequestMethod.POST)
    public String updateBillCategory(HttpServletRequest request, HttpSession session, @PathVariable("id") int billCategoryId, Model model) throws Exception {
        if (currentUserAdmin(session)) {
            String name = request.getParameter("name");
            double cost = Double.valueOf(request.getParameter("cost"));
            CategoryType type = CategoryType.valueOf(request.getParameter("type").toUpperCase());
            Response billCategoryUpdate = billCategoryService.updateBillCategory(billCategoryId, name, cost, type);
            model.addAttribute(Constants.ModelAttributes.MESSAGE, billCategoryUpdate.isSuccessful() ? "Success" : "Failed");
        }
        return Constants.RedirectPage.INDEX;
    }

    private boolean currentUserAdmin(HttpSession session) throws Exception {
        int userId = Integer.valueOf(session.getAttribute(Constants.SessionKeys.LOGGED_IN_USER).toString());
        Response<User> userById = userService.getUserById(userId);
        return userById.isSuccessful() && userById.data().isAdmin();
    }
}
