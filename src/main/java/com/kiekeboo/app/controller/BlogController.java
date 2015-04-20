package com.kiekeboo.app.controller;

import com.kiekeboo.app.services.ServiceStubs;
import com.kiekeboo.app.model.BlogPost;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private ServiceStubs serviceStubs;

    @RequestMapping(method = RequestMethod.GET, value = "/getpost/{id}")
    public @ResponseBody BlogPost getBlogPostId(
            Model model,
            @PathVariable int id) {
        logger.info("/getpost/{}", id);

        BlogPost blogPost = serviceStubs.getBlogPostFromDatabase();

        return blogPost;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getpost")
    public @ResponseBody BlogPost getBlogPost(
            Model model) {
        logger.info("/getpost/");

        BlogPost blogPost = serviceStubs.getBlogPostFromDatabase();

        return blogPost;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getlatestposts")
    public @ResponseBody
    List<BlogPost> getLatestBlogPosts(Model model) {
        List<BlogPost> blogPostList = serviceStubs.getLatestBlogPostsFromDatabase();
        return blogPostList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addpost")
    public @ResponseBody boolean addPost(
            Model model,
            @PathVariable BlogPost blogPost) {
        boolean status = serviceStubs.addBlogPostToDatabase(blogPost);
        return status;
    }
}
