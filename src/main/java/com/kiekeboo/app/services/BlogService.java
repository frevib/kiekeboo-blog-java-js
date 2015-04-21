package com.kiekeboo.app.services;

import com.kiekeboo.app.model.BlogPost;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hdv on 21/04/15.
 */
public class BlogService {

    private SessionFactory sessionFactory;

    public BlogService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    @Override
    @Transactional
    public List<BlogPost> getBlogList() {
        @SuppressWarnings("unchecked")
        List<BlogPost> listUser = (List<BlogPost>) sessionFactory.getCurrentSession()
                .createCriteria(BlogPost.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listUser;
    }


}
