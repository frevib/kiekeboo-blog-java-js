package com.kiekeboo.app.controller;

import com.kiekeboo.app.model.BlogPostDataModel;
import com.kiekeboo.app.model.BlogPostRequestModel;
import com.kiekeboo.app.model.BlogPostResponseModel;
import com.kiekeboo.app.dao.BlogDAO;

import org.hibernate.HibernateException;
import org.hibernate.validator.constraints.Length;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private BlogDAO blogDAO;
    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    public BlogController(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "*")
    public ResponseEntity<BlogPostDataModel> getBlogPost() {
        logger.info("HIT: /*");
        BlogPostDataModel blogPostDataModel;
//        request blog post from database
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
    public ResponseEntity<BlogPostDataModel> getBlogPostById(@Max(9999) @PathVariable int id) {
        logger.info("HIT: /getblogpost/{}", id);
//        Valition for @PathVariable not supported, so created this QnD fix.. better use JAX-RS
        if(id > 9999) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<List<BlogPostResponseModel>> getLatestBlogPosts() {
        logger.info("HIT: /getlatestblogposts");
        List<BlogPostResponseModel> blogPostResponseModelList;
        try {
            blogPostResponseModelList = blogDAO.getLatestBlogPosts();
            logger.info("Successfully fetched latest blogposts from database, returning this data to user");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(blogPostResponseModelList, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/postblogitem", consumes = "application/json")
    public ResponseEntity<String> saveBlogPost(@RequestBody @Valid BlogPostRequestModel blogPostRequestModel, BindingResult bindingResult) throws Exception {
        logger.info("HIT: /postblogitem");
//        Validate request
        final Set<ConstraintViolation<BlogPostRequestModel>> constraintViolations = validator.validate(blogPostRequestModel);
        if (!constraintViolations.isEmpty()) {
            logger.warn("Validation error");
            return new ResponseEntity<>("Item NOT added", HttpStatus.BAD_REQUEST);
        }
//        Check if model binding (JSON -> BlogPostRequestModel) went OK
        if (bindingResult.hasErrors()) {
            logger.warn(bindingResult.toString());
            return new ResponseEntity<>("Item NOT added", HttpStatus.BAD_REQUEST);
        }
//        Map BlogPostRequestModel to BlogPostDataModel
        BlogPostDataModel blogPostDataModel = new BlogPostDataModel();
        blogPostDataModel.mapRequestToDataModel(blogPostRequestModel);
//        Put blog post into the database
        try {
            blogDAO.saveBlogPost(blogPostDataModel);
            logger.info("Blog post successfully saved in database!");
            return new ResponseEntity<>("Item added", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Item NOT added", HttpStatus.BAD_REQUEST);
        }
    }

//    TODO: search controller
}
