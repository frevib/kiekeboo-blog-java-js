package com.kiekeboo.app.services;

import com.kiekeboo.app.model.BlogPost;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ServiceStubs {

    private final Logger logger = LoggerFactory.getLogger(ServiceStubs.class.getName());

    private BlogPost blogPost1 = new BlogPost();
    private BlogPost blogPost2 = new BlogPost();

    private String inject;

    public void setInject(String inject) {
        this.inject = inject;
    }
    public String getInject() {
        return inject;
    }


    public ServiceStubs() {
        blogPost1.setId(1);
        blogPost1.setTitle("Title ONE..1");
        blogPost1.setContents("Contents of first article!");

        blogPost2.setId(2);
        blogPost2.setTitle("Title TWO...2");
        blogPost2.setContents("COntents of SECOND article");
    }

    public BlogPost getBlogPostFromDatabase() {
        return blogPost1;
    }

    public List<BlogPost> getLatestBlogPostsFromDatabase() {
        List<BlogPost> blogPostList = new ArrayList<BlogPost>();
        blogPostList.add(blogPost1);
        blogPostList.add(blogPost2);
        return blogPostList;
    }

    public boolean addBlogPostToDatabase(BlogPost blogPost) {
        boolean status = false;

        return status;
    }



}
