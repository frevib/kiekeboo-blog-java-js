package com.kiekeboo.app.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kiekeboo.app.services.CustomDateSerializerService;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.Date;


public class BlogPostDataModel {

    private int id;
    private String title;
    private String contents;
    private Date date;

    @Length(min = 1, max = 30)
    @Pattern(regexp = "[\\w\\s]*", message = "not a valid writer")
    private String writer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public BlogPostDataModel mapToBlogPostDataModel(BlogPostRequestModel blogPostRequestModel) {
        this.setTitle(blogPostRequestModel.getTitle());
        this.setContents(blogPostRequestModel.getContents());
        this.setDate(new Date());
        // TODO: Fetch writer from session
        this.setWriter("El Escribador");
        return this;
    }

}
