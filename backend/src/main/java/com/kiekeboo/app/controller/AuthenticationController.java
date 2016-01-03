package com.kiekeboo.app.controller;

import com.kiekeboo.app.model.UserDataModel;
import com.kiekeboo.app.model.UserRequestModel;
import com.kiekeboo.app.services.AuthenticateUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AuthenticationController {

    private AuthenticateUserService authenticateUserService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(AuthenticateUserService authenticateUserService) {
        this.authenticateUserService = authenticateUserService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json")
    public String authenticateUser(Model model, @RequestBody @Valid UserRequestModel userRequestModel, BindingResult bindingResult) {
        logger.info("HIT: /login");
        if(bindingResult.hasErrors()) {
            logger.warn("invalid request");
//           TODO: return HTTP 2xx or something else than just 200 and a string
            return "request not valid";
        }

        UserDataModel userDataModel = new UserDataModel();
        userDataModel = userDataModel.mapRequestToDataModel(userRequestModel);

//        check password sent in JSON {"username":"xxxx", "password":"yyy"}
        String userAuthenticatedToken = authenticateUserService.authenticate(userDataModel);
        if(userAuthenticatedToken != null) {
//              Return token as Authenticate header
//              Return HTTP 200
            return userAuthenticatedToken;
        }
        return "NO_TOKEN";
    }

}
