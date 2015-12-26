package com.kiekeboo.app.controller;

import com.kiekeboo.app.services.BlogService;
import com.kiekeboo.app.model.BlogPost;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;
    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // TODO: Not working with @Requestbody
    @InitBinder("writer")
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("writer");
//        binder.setRequiredFields("username","password","emailAddress");
    }

    @RequestMapping(method = RequestMethod.GET, value = "*")
    public BlogPost getBlogPost(Model model) {
        logger.info("URL: /*");
        BlogPost blogPost = blogService.getBlogPostById(1);
        return blogPost;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getblogpost/{id}")
    public BlogPost getBlogPostById(Model model, @PathVariable int id) {
        logger.info("URL: /getblogpost/{}", id);
        BlogPost blogPost;
        try {
            blogPost = blogService.getBlogPostById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // return empty
        }
        return blogPost;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getlatestblogposts")
    public List<BlogPost> getLatestBlogPosts(Model model) {
        logger.info("URL: /getlatestblogposts");
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
    public boolean saveBlogPost(Model model, @RequestBody @Valid BlogPost blogPost, BindingResult bindingResult) {
        try {
// TODO: add validation, length for title and contents
            blogPost.setDate(new Date());
//            blogPost.setWriter("---writer name from session---");
            String[] suppressedFields = bindingResult.getSuppressedFields();
            for(String element : suppressedFields) {
                logger.info(element);

            }

            if(bindingResult.hasErrors()) {
                logger.info(bindingResult.toString());
            }
            blogService.saveBlogPost(blogPost);

            return true;
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            return false;
        }
    }

//    TODO: search controller
}
