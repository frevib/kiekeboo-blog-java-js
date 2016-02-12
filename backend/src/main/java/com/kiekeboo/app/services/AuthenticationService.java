package com.kiekeboo.app.services;

import com.kiekeboo.app.dao.AuthenticationDAO;
import com.kiekeboo.app.model.PasswordAndSaltModel;
import com.kiekeboo.app.model.UserDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationService {

    private AuthenticationDAO authenticationDAO;
    private JWTTokenService JWTTokenService;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    public AuthenticationService(final AuthenticationDAO authenticationDAO,
                                 final JWTTokenService JWTTokenService) {
        this.authenticationDAO = authenticationDAO;
        this.JWTTokenService = JWTTokenService;
    }

    /**
     * Authenticate user based on username and password, return a Json web token.
     *
     * @param userDataFromPost
     * @return String JsonWebToken
     * @throws Exception
     */
    public String authenticate(UserDataModel userDataFromPost) throws Exception {
        logger.info("LOGIN: user {} tries to login", userDataFromPost.getUsername());
        //  TODO: could be nicer written logic
        //  TODO: CRITICAL, fix throws!
        UserDataModel user;

        // Fetch user information from database.
        try {
            user = authenticationDAO.getUserFromDatabase(userDataFromPost.getUsername());
            logger.info("Fetched user object from database");
        } catch (Exception e) {
            logger.warn("Could not fetch password and salt from database");
            throw new Exception("Could not fetch password and salt from database");
        }

        // Now the user information is know, check if the credentials given by the user are correct.
        // If correct (login OK), return a JsonWebToken (authentication token) to the user.
        PasswordAndSaltModel passwordAndSaltFromDatabase = getPasswordAndSalt(user);
        String unhashedPasswordFromPost = userDataFromPost.getPassword();
        if(passwordAndSaltFromDatabase == null) {
            throw new Exception("Password and salt fetched from database are null");
        }
        if (PasswordService.checkPassword(passwordAndSaltFromDatabase, unhashedPasswordFromPost)) {
            String token = JWTTokenService.getNewToken(user);
            if(token == null){
                throw new Exception("No token generated");
            }
            return token;
        }
        logger.warn("Incorrect password for user {}", userDataFromPost.getUsername());
        throw new Exception();
    }

    // Extract password and salt from the user object.
    private PasswordAndSaltModel getPasswordAndSalt(UserDataModel user) {
        PasswordAndSaltModel passwordAndSaltModel = new PasswordAndSaltModel();
        passwordAndSaltModel.setPassword(user.getPassword());
        passwordAndSaltModel.setSalt(user.getSalt());
        return passwordAndSaltModel;
    }
}
