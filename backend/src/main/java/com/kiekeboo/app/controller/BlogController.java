package com.kiekeboo.app.controller;

import com.kiekeboo.app.model.BlogPostDataModel;
import com.kiekeboo.app.model.BlogPostRequestModel;
import com.kiekeboo.app.model.BlogPostResponseModel;
import com.kiekeboo.app.services.BlogService;

import org.hibernate.validator.constraints.Length;
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

@Controller
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;
    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "*")
    public BlogPostDataModel getBlogPost(Model model) {
        logger.info("URL: /*");
        BlogPostDataModel blogPostResponseModel = blogService.getBlogPostById(1);
        return blogPostResponseModel;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getblogpost/{id}")
    public BlogPostDataModel getBlogPostById(Model model, @Length(min = 0, max = 99999) @PathVariable int id) {
        logger.info("URL: /getblogpost/{}", id);
        BlogPostDataModel blogPostDataModel;
        try {
            blogPostDataModel = blogService.getBlogPostById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // return empty
        }
        return blogPostDataModel;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getlatestblogposts")
    public List<BlogPostDataModel> getLatestBlogPosts(Model model) {
        logger.info("URL: /getlatestblogposts");
        List<BlogPostDataModel> blogPostDataModelList;
        try {
            blogPostDataModelList = blogService.getLatestBlogPosts();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // return empty
        }
        return blogPostDataModelList;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/postblogitem", consumes="application/json")
    public boolean saveBlogPost(Model model, @RequestBody @Valid BlogPostRequestModel blogPostRequestModel, BindingResult bindingResult) {
        try {

            BlogPostDataModel blogPostDataModel = new BlogPostDataModel();
            blogPostDataModel.mapToBlogPostDataModel(blogPostRequestModel);

            String[] suppressedFields = bindingResult.getSuppressedFields();
            for(String element : suppressedFields) {
                logger.info(element);

            }

            if(bindingResult.hasErrors()) {
                logger.info(bindingResult.toString());
            }

            blogService.saveBlogPost(blogPostDataModel);
//            TODO: change this to something better that true/false
            return true;

        } catch (Exception e) {
            logger.info(String.valueOf(e));
            return false;
        }
    }

//    TODO: search controller
}