package com.kiekeboo.app.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kiekeboo.app.model.interfaces.BlogPostInterface;
import com.kiekeboo.app.services.CustomDateSerializerService;
import com.kiekeboo.app.validation.TitleValidate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class BlogPostDataModel implements BlogPostInterface {

    private int blogpostId;
    private Date date;
    private String writer;
    private String title;
    private String contents;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getBlogpostId() {
        return blogpostId;
    }

    public void setBlogpostId(int id) {
        this.blogpostId = id;
    }

    @JsonSerialize(using = CustomDateSerializerService.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public BlogPostDataModel mapRequestToDataModel(BlogPostRequestModel blogPostRequestModel) {
        this.setTitle(blogPostRequestModel.getTitle());
        this.setContents(blogPostRequestModel.getContents());
        this.setDate(new Date());
        // TODO: Fetch writer from session/ Database
        this.setWriter("El Escribador");
        return this;
    }

}
