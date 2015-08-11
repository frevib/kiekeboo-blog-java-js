package com.kiekeboo.app.controller;

import com.kiekeboo.app.services.BlogService;
import com.kiekeboo.app.model.BlogPost;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

//    @ModelAttribute("blogpost")
//    public BlogPost getBlogPost() {
//        return new BlogPost();
//    }

    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @RequestMapping(method = RequestMethod.GET, value = "*")
    public BlogPost getBlogPost(Model model) {
        logger.info("URL: /*");
        BlogPost blogPost = blogService.getBlogPostById(1);
        return blogPost;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getblogpost/{id}")
    public BlogPost getBlogPostById(Model model, @PathVariable int id) {
        logger.info("URL: /getpost/{}", id);
        BlogPost blogPost;
        try {
            blogPost = blogService.getBlogPostById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // return empty
        }
        return blogPost;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getlatestblogposts")
    public List<BlogPost> getLatestBlogPosts(Model model) {
        List<BlogPost> blogPostList;
        try {
            blogPostList = blogService.getLatestBlogPosts();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // return empty
        }
        return blogPostList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/postblogitem", consumes="application/json")
    public boolean saveBlogPost(Model model,
                                @RequestBody
                                @Valid
                                BlogPost blogPost, BindingResult bindingResult) {
        try {
//            TODO: add validation, length for title and contents
            blogPost.setDate(new Date());
            blogPost.setWriter("---writer name from session---");
            blogService.saveBlogPost(blogPost);

            return true;
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            return false;
        }
    }

//    TODO: search controller
}
