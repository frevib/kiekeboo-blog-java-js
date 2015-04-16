package com.kiekeboo.app.controller;

import com.kiekeboo.app.DOA.DaoStubs;
import com.kiekeboo.app.model.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pages")
public class PagesController {

    @Autowired
    private DaoStubs daoStubs;

    @RequestMapping(method = RequestMethod.GET, value = "/getpost")
    public @ResponseBody BlogPost showBlogPost(Model model) {
        BlogPost blogPost = daoStubs.getBlogPostObject();

        return blogPost;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getposts")
    public @ResponseBody List<BlogPost> showBlogPosts(Model model) {
        DaoStubs daoStubs = new DaoStubs();
        List<BlogPost> blogPostList = daoStubs.getBlogPostObjects();
        return blogPostList;
    }
}
