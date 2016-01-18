package com.kiekeboo.app.controller;

import com.kiekeboo.app.model.JsonWebToken;
import com.kiekeboo.app.model.UserDataModel;
import com.kiekeboo.app.model.UserRequestModel;
import com.kiekeboo.app.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class AuthenticationController {

    private AuthenticationService authenticationService;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json", produces = "application/json")
//    TODO: change responses to login OK/200 etc.
    public ResponseEntity<JsonWebToken> authenticateUser(@RequestBody @Valid UserRequestModel userRequestModel, BindingResult bindingResult) {
        logger.info("HIT: /login");
//        Check if model binding (JSON -> BlogPostRequestModel) went OK
        if (bindingResult.hasErrors()) {
            logger.warn("invalid request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDataModel userDataModel = new UserDataModel();
        userDataModel = userDataModel.mapRequestToDataModel(userRequestModel);
//        Check password sent in JSON {"username":"xxxx", "password":"yyyy"}
        String userAuthenticatedToken;
        try {
            userAuthenticatedToken = authenticationService.authenticate(userDataModel);
            logger.info("User successfully authenticated, token generated");
        } catch (Exception e) {
            logger.warn("Authentication failed");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (userAuthenticatedToken != null) {
            logger.info("Request OK, fetching TOKEN");
//              TODO: Return token as Authorization Bearer header
            JsonWebToken jwt = new JsonWebToken();
            jwt.setValue(userAuthenticatedToken);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }
        logger.info("Login failed, no token served");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
