package com.kiekeboo.app.dao;

import com.kiekeboo.app.model.UserDataModel;


import java.util.List;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AuthenticationDAO {

    private SessionFactory sessionFactory;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationDAO.class);

    @Autowired
    public AuthenticationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    Temp always true for testing


    @Transactional
    public List<UserDataModel> getUserFromDatabase(String username) throws HibernateException {
        String hql = "FROM UserDataModel U WHERE U.username = :username";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("username", username);
        List<UserDataModel> userData = (List<UserDataModel>) query.list();
        return userData;
    }

}
