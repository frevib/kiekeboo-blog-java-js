package com.kiekeboo.app.controller;

import com.kiekeboo.app.dao.BlogDAO;
import com.kiekeboo.app.model.BlogPostDataModel;
import com.kiekeboo.app.model.BlogPostRequestModel;
import com.kiekeboo.app.model.BlogPostResponseModel;
import com.kiekeboo.app.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private BlogDAO blogDAO;
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    public AdminController(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/addpost", consumes = "application/json")
    public ResponseEntity<String> saveBlogPost(@RequestBody @Valid BlogPostRequestModel blogPostRequestModel, BindingResult bindingResult) throws Exception {
        logger.info("HIT: /postblogitem");
//        Check if model binding (JSON -> BlogPostRequestModel) went OK
        if (bindingResult.hasErrors()) {
            logger.warn("Error in bindingresult: {}", bindingResult.toString());
            return new ResponseEntity<>("Item NOT added, invalid user input", HttpStatus.BAD_REQUEST);
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
}
