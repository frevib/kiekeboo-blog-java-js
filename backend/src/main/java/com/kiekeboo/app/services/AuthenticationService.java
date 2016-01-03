package com.kiekeboo.app.services;

import com.kiekeboo.app.model.UserDataModel;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AuthenticationService {

    private SessionFactory sessionFactory;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    public AuthenticationService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    Temp always true for testing


    @Transactional
    public UserDataModel getUserFromDatabase(String usernameFromPost) throws HibernateException {
        UserDataModel userDataFromDatabase = (UserDataModel) sessionFactory.getCurrentSession().createQuery("from USERS where username = :userNameFromPost");
        return userDataFromDatabase;
    }

}
