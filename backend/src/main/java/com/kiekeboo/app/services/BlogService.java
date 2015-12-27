package com.kiekeboo.app.services;

import com.kiekeboo.app.model.BlogPostDataModel;
import com.kiekeboo.app.model.BlogPostRequestModel;
import com.kiekeboo.app.model.BlogPostResponseModel;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BlogService {

    private SessionFactory sessionFactory;

    public BlogService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<BlogPostDataModel> getLatestBlogPosts() throws HibernateException {
        @SuppressWarnings("unchecked")
        List<BlogPostDataModel> blogPostResponseModelList = (List<BlogPostDataModel>) sessionFactory.getCurrentSession()
                .createCriteria(BlogPostDataModel.class)
                .setMaxResults(10)
                .addOrder(Order.desc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();

        return blogPostResponseModelList;
    }

    @Transactional
    public void saveBlogPost(BlogPostDataModel blogPostDataModel) throws HibernateException {
        sessionFactory.getCurrentSession().save(blogPostDataModel);
    }

    @Transactional
    public BlogPostDataModel getBlogPostById(int id) throws HibernateException {
        BlogPostDataModel blogPostDataModel = (BlogPostDataModel) sessionFactory.getCurrentSession().get(BlogPostDataModel.class, id);
        return blogPostDataModel;
    }


}
