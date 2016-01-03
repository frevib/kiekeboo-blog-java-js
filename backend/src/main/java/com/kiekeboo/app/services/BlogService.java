package com.kiekeboo.app.services;

import com.kiekeboo.app.model.BlogPostDataModel;
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

//    Get X latest blog posts from database
    @Transactional
    public List<BlogPostResponseModel> getLatestBlogPosts() throws HibernateException {
        List<BlogPostResponseModel> blogPostResponseModelList = (List<BlogPostResponseModel>) sessionFactory.getCurrentSession()
                .createCriteria(BlogPostDataModel.class)
                .setMaxResults(10)
                .addOrder(Order.desc("blogpostId"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();

        return blogPostResponseModelList;
    }

//    Add a blogpost to the database
    @Transactional
    public void saveBlogPost(BlogPostDataModel blogPostDataModel) throws HibernateException {
        sessionFactory.getCurrentSession().save(blogPostDataModel);
    }

//    Get specific blogpost from database by id
    @Transactional
    public BlogPostDataModel getBlogPostById(int geit) throws HibernateException {
//        TODO: cast objects of different types, BlogPostDataModel and BlogPostResponseModel
        BlogPostDataModel blogPostDataModel = (BlogPostDataModel) sessionFactory.getCurrentSession().get(BlogPostDataModel.class, geit);
        return blogPostDataModel;
    }


}
