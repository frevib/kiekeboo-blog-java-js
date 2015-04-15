package com.kiekeboo.app.controller;

import com.kiekeboo.app.model.BlogPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pages")
public class PagesController {

    @RequestMapping(method = RequestMethod.GET, value = "/beginpage")
    public @ResponseBody BlogPost showBlogPost(Model model) {
        BlogPost blogPost = new BlogPost();


        return blogPost;
    }
}
