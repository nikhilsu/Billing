package com.billing.controller;

import com.billing.helper.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(Constants.Route.ROOT)
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String showLandingPage() {
        return Constants.RedirectPage.INDEX;
    }
}
