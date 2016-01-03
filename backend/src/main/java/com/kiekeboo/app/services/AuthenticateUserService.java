package com.kiekeboo.app.services;

import com.kiekeboo.app.dao.AuthenticationDAO;
import com.kiekeboo.app.model.PasswordAndSaltModel;
import com.kiekeboo.app.model.UserDataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticateUserService {

    private AuthenticationDAO authenticationDAO;

    private final Logger logger = LoggerFactory.getLogger(AuthenticateUserService.class);

    @Autowired
    public AuthenticateUserService(AuthenticationDAO authenticationDAO) {
        this.authenticationDAO = authenticationDAO;
    }

//    authenticate user based on username and password, return some kind of token (JWS?)
    public String authenticate(UserDataModel userDataFromPost) {
        logger.info("LOGIN: user {} tries to login", userDataFromPost.getUsername());
        String unhashedPasswordFromPost = userDataFromPost.getPassword();
//        TODO: could be nicer written logic
        PasswordAndSaltModel passwordAndSalt = getPasswordAndSaltFromDataBase(userDataFromPost.getUsername());
        if(passwordAndSalt == null) {
            return null;
        }
        if (PasswordService.checkHashedPassword(passwordAndSalt, unhashedPasswordFromPost)) {
//            TODO: generate token (JWS?)
            return "TOKEN!";
        }
        return null;
    }

    private PasswordAndSaltModel getPasswordAndSaltFromDataBase(String username) {

        List<UserDataModel> userDataFromDatabase = authenticationDAO.getUserFromDatabase(username);

//        TODO: both logging and throwing
        if(userDataFromDatabase.size() != 1) {
            logger.error("No user found, or more than one user in resultset (!)");
            return null;
        }
        logger.info("Fetched password for username: {} from database", userDataFromDatabase.get(0).getUsername());

        PasswordAndSaltModel passwordAndSaltModel = new PasswordAndSaltModel();
        passwordAndSaltModel.setPassword(userDataFromDatabase.get(0).getPassword());
        passwordAndSaltModel.setSalt(userDataFromDatabase.get(0).getSalt());
        return passwordAndSaltModel;
    }


}
