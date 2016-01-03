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
    public String authenticate(UserDataModel userDataFromPost) {
        logger.info("LOGIN: user %s tries to login", userDataFromPost.getUsername());

        //        get username, password from POST request
        String usernameFromPost = userDataFromPost.getUsername();
        String passwordFromPost = userDataFromPost.getPassword();

//        get password by username from POST request from database
//        Fetch by email is better (for later)
        UserDataModel userDataFromDatabase = authenticationService.getUserFromDatabase(usernameFromPost);


//        check if password from POST request == password from database
//        use awesome password checker with XOR-ing

//        if password is OK
//        if(authenticationService.validateCredentialsInDatabase(userDataModel)) {
//
////            generate and return token
//            return "JWS TOKEN";
//        }
        return null;
    }


}
