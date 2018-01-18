package com.billing.controller.auth;

import com.billing.helper.Constants;
import com.billing.helper.Response;
import com.billing.model.UserRole;
import com.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(Constants.Route.REGISTER)
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm() {
        return Constants.RedirectPage.REGISTRATION_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        Response save = userService.createUser(firstName, lastName, userId, password, UserRole.USER);
        model.addAttribute(Constants.ModelAttributes.RESULT, save.isSuccessful());
        if (save.isSuccessful()) {
            session.setAttribute(Constants.SessionKeys.LOGGED_IN_USER, save.data());
            return Constants.Route.REDIRECT + Constants.Route.ROOT;
        }

        model.addAttribute(Constants.ModelAttributes.MESSAGE, save.errors().get(0));
        return Constants.RedirectPage.REGISTRATION_FORM;
    }
}
