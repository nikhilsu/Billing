package com.billing.controller;

import com.billing.helper.Constants;
import com.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(Constants.Route.ROOT)
public class IndexController extends BaseController {

    @Autowired
    public IndexController(UserService userService) {
        super(userService);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showLandingPage(HttpSession session, Model model) throws Exception {
        model.addAttribute(Constants.ModelAttributes.IS_ADMIN, currentUserAdmin(session));
        return Constants.RedirectPage.INDEX;
    }
}
