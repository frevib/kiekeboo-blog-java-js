package com.kiekeboo.app.dao;

import com.kiekeboo.app.model.BlogPostDataModel;
import com.kiekeboo.app.model.BlogPostViewModel;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BlogDAO {

    private SessionFactory sessionFactory;
    public BlogDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    private static final int NUMBER_OF_BLOGPOSTS = 10;

    // Get X latest blog posts from database.
    @Transactional
    public List<BlogPostViewModel> getLatestBlogPosts() throws HibernateException {
        // TODO: return data in BlogPostDataModel persistent object, then move to BlogPostViewModel
        List<BlogPostViewModel> blogPostViewModelList = (List<BlogPostViewModel>) sessionFactory.getCurrentSession()
                .createCriteria(BlogPostDataModel.class)
                .setMaxResults(NUMBER_OF_BLOGPOSTS)
                .addOrder(Order.desc("blogpostId"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        return blogPostViewModelList;
    }

    // Add a blogpost to the database.
    @Transactional
    public void saveBlogPost(BlogPostDataModel blogPostDataModel) throws HibernateException {
        sessionFactory.getCurrentSession().save(blogPostDataModel);
    }

    // Get specific blogpost from database by id.
    @Transactional
    public BlogPostDataModel getBlogPostById(int id) throws HibernateException {
        // TODO: cast objects of different types, BlogPostDataModel and BlogPostViewModel
        // TODO: return data in blogPostDataModel persistent object, then move to BlogPostViewModel
        BlogPostDataModel blogPostDataModel = (BlogPostDataModel) sessionFactory.getCurrentSession().get(BlogPostDataModel.class, id);
        return blogPostDataModel;
    }


}
