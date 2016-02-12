package com.kiekeboo.app.controller;

import com.kiekeboo.app.model.BlogPostDataModel;
import com.kiekeboo.app.model.BlogPostViewModel;
import com.kiekeboo.app.dao.BlogDAO;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private BlogDAO blogDAO;
    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    public BlogController(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "*")
    // TODO: must return ViewModel
    public ResponseEntity<BlogPostDataModel> getBlogPost() {
        logger.info("HIT: /*");
        BlogPostDataModel blogPostDataModel;

        // Fetch blog post from database.
        try {
            blogPostDataModel = blogDAO.getBlogPostById(1);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(blogPostDataModel, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getblogpost/{id}")
    public ResponseEntity<BlogPostDataModel> getBlogPostById(@PathVariable int id) {
        logger.info("HIT: /getblogpost/{}", id);

        // Validation for @PathVariable not supported, so created this QnD fix.. better use JAX-RS
        if(id < 1 && id > 99999) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Fetch blog post from database by id.
        BlogPostDataModel blogPostDataModel;
        try {
            blogPostDataModel = blogDAO.getBlogPostById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(blogPostDataModel, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getlatestblogposts")
    public ResponseEntity<List<BlogPostViewModel>> getLatestBlogPosts() {
        logger.info("HIT: /getlatestblogposts");

        // Fetch blog posts from database.
        List<BlogPostViewModel> blogPostViewModelList;
        try {
            blogPostViewModelList = blogDAO.getLatestBlogPosts();
            logger.info("Successfully fetched latest blogposts from database, returning this data to user");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(blogPostViewModelList, HttpStatus.OK);
    }

//    TODO: search controller
}
