package com.kiekeboo.app.services;

import com.kiekeboo.app.model.UserDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticateUser {

    private AuthenticationService authenticationService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticateUser.class);

    @Autowired
    public AuthenticateUser(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

//    authenticate user based on username and password, return some kind of token (JWS?)
    public String authenticate(UserDataModel userDataModel) {
        logger.info("LOGIN: user %s tries to login", userDataModel.getUsername());

//        if password is OK
        if(authenticationService.validateCredentialsInDatabase()) {
            return "JWS TOKEN";
        }
        return null;
    }


}
