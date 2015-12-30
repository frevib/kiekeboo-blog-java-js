package com.kiekeboo.app.services;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationService {

    private SessionFactory sessionFactory;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    public AuthenticationService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    Temp always true for testing
    public boolean validateCredentialsInDatabase() {
        return true;

    }
}
