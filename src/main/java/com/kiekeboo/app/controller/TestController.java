package com.kiekeboo.app.controller;

import com.kiekeboo.app.model.BlogPost;
import com.kiekeboo.app.services.BlogService;
import com.kiekeboo.app.services.ServiceStubs;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.kiekeboo.app.model.TestModel;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    BlogService blogService;

    @ModelAttribute("blogpost")
    public BlogPost getBlogPost() {
        return new BlogPost();
    }

    @RequestMapping("/")
    public String printBeginpage() {
        return "beginpage";
    }

    // HTTP test request
    @RequestMapping("/kiekeboo")
    public String printKiekeboo(ModelMap model) {
        model.addAttribute("message", "Kiekeboo initial testcontroller!");
        return "kiekeboo";
    }

    // JSON test service
    @RequestMapping(method = RequestMethod.GET, value = "/services/test/{name}/{id}")
    public
    @ResponseBody
    TestModel echoJsonObject(
            @PathVariable String name,
            @PathVariable int id) {
        TestModel testModel = new TestModel();
        testModel.setId(id);
        testModel.setName(name);
        return testModel;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/echoaddpost")
    public
    @ResponseBody
    BlogPost echoAddPost(
            Model model,
            @ModelAttribute("blogpost") BlogPost blogPost, BindingResult result) {
        BlogPost modelpost = blogPost;
        return modelpost;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getbloglist")
    public
    @ResponseBody
    List<BlogPost> getBlogPostId(
            Model model) {
        logger.info("/getbloglist");
        List<BlogPost> blogList = null;
        try {
            blogList = blogService.getLatestBlogPosts();
        } catch (HibernateException e) {
            logger.info(String.valueOf(e));
        }
        return blogList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveblogpost")
    public @ResponseBody boolean saveBlogPost(
            Model model,
            @ModelAttribute("blogpost") BlogPost blogPost, BindingResult result) {
        try {
            blogService.saveBlogPost(blogPost);
            return true;
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            return false;
        }
    }

}