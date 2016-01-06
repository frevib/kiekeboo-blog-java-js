package com.kiekeboo.app.services;

import com.kiekeboo.app.dao.AuthenticationDAO;
import com.kiekeboo.app.model.PasswordAndSaltModel;
import com.kiekeboo.app.model.UserDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationService {

    private AuthenticationDAO authenticationDAO;
    private UserTokenService userTokenService;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    public AuthenticationService(final AuthenticationDAO authenticationDAO,
                                 final UserTokenService userTokenService) {
        this.authenticationDAO = authenticationDAO;
        this.userTokenService = userTokenService;
    }

//    authenticate user based on username and password, return some kind of token (JWS?)
    public String authenticate(UserDataModel userDataFromPost) throws Exception {
        logger.info("LOGIN: user {} tries to login", userDataFromPost.getUsername());
//        TODO: could be nicer written logic
//        TODO: CRITICAL fix throws!
        UserDataModel user;
        try {
            user = authenticationDAO.getUserFromDatabase(userDataFromPost.getUsername());
            logger.info("Fetched user object from database");
        } catch (Exception e) {
            logger.warn("Could not fetch password and salt from database");
            throw new Exception("Could not fetch password and salt from database");
        }
        PasswordAndSaltModel passwordAndSaltFromDatabase = getPasswordAndSalt(user);
        String unhashedPasswordFromPost = userDataFromPost.getPassword();
        if(passwordAndSaltFromDatabase == null) {
            throw new Exception("Password and salt fetched from database are null");
        }
        if (PasswordService.checkPassword(passwordAndSaltFromDatabase, unhashedPasswordFromPost)) {
//            TODO: generate token (JWT?)
            String token = userTokenService.getNewToken(user);
            if(token == null){
                throw new Exception("No token generated");
            }
            return token;
        }
        logger.warn("Incorrect password for user {}", userDataFromPost.getUsername());
        throw new Exception();
    }

    private PasswordAndSaltModel getPasswordAndSalt(UserDataModel user) {
        PasswordAndSaltModel passwordAndSaltModel = new PasswordAndSaltModel();
        passwordAndSaltModel.setPassword(user.getPassword());
        passwordAndSaltModel.setSalt(user.getSalt());
        return passwordAndSaltModel;
    }


}
