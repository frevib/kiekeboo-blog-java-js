package com.kiekeboo.app.DOA;

import com.kiekeboo.app.model.BlogPost;

import java.util.ArrayList;
import java.util.List;

public class DaoStubs {

    private BlogPost blogPost1 = new BlogPost();
    private BlogPost blogPost2 = new BlogPost();

    public DaoStubs() {
        blogPost1.setId(1);
        blogPost1.setTitle("Title ONE..1");
        blogPost1.setContents("Contents of first article!");

        blogPost2.setId(2);
        blogPost2.setTitle("Title TWO...2");
        blogPost2.setContents("COntents of SECOND article");
    }

    public BlogPost getBlogPostObject() {
        return blogPost1;
    }

    public List<BlogPost> getBlogPostObjects() {
        List<BlogPost> blogPostList = new ArrayList<BlogPost>();
        blogPostList.add(blogPost1);
        blogPostList.add(blogPost2);
        return blogPostList;
    }


}
