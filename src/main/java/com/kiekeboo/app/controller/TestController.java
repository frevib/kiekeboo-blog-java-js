package com.kiekeboo.app.controller;

import com.kiekeboo.app.model.BlogPost;
import com.kiekeboo.app.services.ServiceStubs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.kiekeboo.app.model.TestModel;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    ServiceStubs serviceStubs;

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
    public @ResponseBody TestModel echoJsonObject(
            @PathVariable String name,
            @PathVariable int id) {
        TestModel testModel = new TestModel();
        testModel.setId(id);
        testModel.setName(name);
        return testModel;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/echoaddpost")
    public @ResponseBody BlogPost echoAddPost(
            Model model,
            @ModelAttribute("blogpost") BlogPost blogPost, BindingResult result) {
        BlogPost modelpost = blogPost;
//        boolean status = serviceStubs.addBlogPostToDatabase(blogPost);
        return modelpost;
    }

}