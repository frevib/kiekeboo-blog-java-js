package com.kiekeboo.app.controller;

import com.kiekeboo.app.services.BlogService;
import com.kiekeboo.app.services.ServiceStubs;
import com.kiekeboo.app.model.BlogPost;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private ServiceStubs serviceStubs;

    @Autowired
    private BlogService blogService;

    @ModelAttribute("blogpost")
    public BlogPost getBlogPost() {
        return new BlogPost();
    }

    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @RequestMapping(method = RequestMethod.GET, value = "*")
    public BlogPost getBlogPost(Model model) {
        logger.info("URL: /getpost/");
        BlogPost blogPost = serviceStubs.getBlogPostFromDatabase();
        return blogPost;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getblogpost/{id}")
    public BlogPost getBlogPostById(Model model, @PathVariable int id) {
        logger.info("URL: /getpost/{}", id);
        BlogPost blogPost = null;
        try {
            blogPost = blogService.getBlogPostById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return blogPost; // return empty
        }
        return blogPost;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getlatestblogposts")
    public List<BlogPost> getLatestBlogPosts(Model model) {
        List<BlogPost> blogPostList = null;
        try {
            blogPostList = blogService.getLatestBlogPosts();
        } catch (Exception e) {
            e.printStackTrace();
            return blogPostList; // return empty
        }
        return blogPostList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveblogpost")
    public boolean saveBlogPost(Model model, @ModelAttribute("blogpost") BlogPost blogPost, BindingResult result) {
        try {
            blogService.saveBlogPost(blogPost);
            return true;
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            return false;
        }
    }
}
