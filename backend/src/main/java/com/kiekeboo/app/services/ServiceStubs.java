package com.kiekeboo.app.services;

import com.kiekeboo.app.model.BlogPostResponseModel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ServiceStubs {

    private final Logger logger = LoggerFactory.getLogger(ServiceStubs.class.getName());

    private BlogPostResponseModel blogPostResponseModel1 = new BlogPostResponseModel();
    private BlogPostResponseModel blogPostResponseModel2 = new BlogPostResponseModel();

    private String inject;

    public void setInject(String inject) {
        this.inject = inject;
    }
    public String getInject() {
        return inject;
    }


    public ServiceStubs() {
        blogPostResponseModel1.setId(1);
        blogPostResponseModel1.setTitle("Title ONE..1");
        blogPostResponseModel1.setContents("Contents of first article!");

        blogPostResponseModel2.setId(2);
        blogPostResponseModel2.setTitle("Title TWO...2");
        blogPostResponseModel2.setContents("COntents of SECOND article");
    }

    public BlogPostResponseModel getBlogPostFromDatabase() {
        return blogPostResponseModel1;
    }

    public List<BlogPostResponseModel> getLatestBlogPostsFromDatabase() {
        List<BlogPostResponseModel> blogPostResponseModelList = new ArrayList<BlogPostResponseModel>();
        blogPostResponseModelList.add(blogPostResponseModel1);
        blogPostResponseModelList.add(blogPostResponseModel2);
        return blogPostResponseModelList;
    }

    public boolean addBlogPostToDatabase(BlogPostResponseModel blogPostResponseModel) {
        boolean status = false;

        return status;
    }



}
