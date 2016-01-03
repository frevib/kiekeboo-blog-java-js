package com.kiekeboo.app.controller;

import com.kiekeboo.app.model.BlogPostDataModel;
import com.kiekeboo.app.model.BlogPostRequestModel;
import com.kiekeboo.app.model.BlogPostResponseModel;
import com.kiekeboo.app.dao.BlogDAO;

import org.hibernate.validator.constraints.Length;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public BlogPostDataModel getBlogPost(Model model) {
        logger.info("URL: /*");
        BlogPostDataModel blogPostDataModel = blogDAO.getBlogPostById(1);
//        Added 'redundant' variable for Clarity, thanks for reminding me Intellij...
        return blogPostDataModel;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getblogpost/{id}")
    public BlogPostDataModel getBlogPostById(Model model, @Length(min = 0, max = 99999) @PathVariable int id) {
        logger.info("URL: /getblogpost/{}", id);
        BlogPostDataModel blogPostDataModel;
        try {
            blogPostDataModel = blogDAO.getBlogPostById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // return empty
        }
        return blogPostDataModel;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getlatestblogposts")
    public List<BlogPostResponseModel> getLatestBlogPosts(Model model) {
        logger.info("URL: /getlatestblogposts");
        List<BlogPostResponseModel> blogPostResponseModelList;
        try {
            blogPostResponseModelList = blogDAO.getLatestBlogPosts();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // return empty
        }
        return blogPostResponseModelList;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/postblogitem", consumes = "application/json")
    public boolean saveBlogPost(Model model, @RequestBody @Valid BlogPostRequestModel blogPostRequestModel, BindingResult bindingResult) throws Exception {
        try {

            if(bindingResult.hasErrors()) {
                logger.warn(bindingResult.toString());
                return false;
            }

            BlogPostDataModel blogPostDataModel = new BlogPostDataModel();
            blogPostDataModel.mapRequestToDataModel(blogPostRequestModel);

            String[] suppressedFields = bindingResult.getSuppressedFields();
            for(String element : suppressedFields) {
                logger.info(element);

            }

            blogDAO.saveBlogPost(blogPostDataModel);
//            TODO: change this to something better that true/false
            return true;

        } catch (Exception e) {
            logger.info(String.valueOf(e));
            return false;
        }
    }

//    TODO: search controller
}
