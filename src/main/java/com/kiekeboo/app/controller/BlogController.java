package com.kiekeboo.app.controller;

import com.kiekeboo.app.services.ServiceStubs;
import com.kiekeboo.app.model.BlogPost;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private ServiceStubs serviceStubs;

    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @ModelAttribute("blogpost")
    public BlogPost getBlogPost() {
        return new BlogPost();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getpost")
    public @ResponseBody BlogPost getBlogPost(
            Model model) {
        logger.info("/getpost/");

        BlogPost blogPost = serviceStubs.getBlogPostFromDatabase();

        return blogPost;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getpost/{id}")
    public @ResponseBody BlogPost getBlogPostId(
            Model model,
            @PathVariable int id) {
        logger.info("/getpost/{}", id);

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
            @ModelAttribute BlogPost blogPost) {
        boolean status = serviceStubs.addBlogPostToDatabase(blogPost);
        return status;
    }
}
