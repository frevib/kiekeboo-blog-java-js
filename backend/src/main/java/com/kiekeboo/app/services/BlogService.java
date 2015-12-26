package com.kiekeboo.app.services;

import com.kiekeboo.app.model.BlogPost;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BlogService {

    private SessionFactory sessionFactory;

    public BlogService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<BlogPost> getLatestBlogPosts() throws HibernateException {
        @SuppressWarnings("unchecked")
        List<BlogPost> blogPostList = (List<BlogPost>) sessionFactory.getCurrentSession()
                .createCriteria(BlogPost.class)
                .setMaxResults(10)
                .addOrder(Order.desc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();

        return blogPostList;
    }

    @Transactional
    public void saveBlogPost(BlogPost blogPost) throws HibernateException {
        sessionFactory.getCurrentSession().save(blogPost);
    }

    @Transactional
    public BlogPost getBlogPostById(int id) throws HibernateException {
        BlogPost blogPost = (BlogPost) sessionFactory.getCurrentSession().get(BlogPost.class, id);
        return blogPost;
    }


}
