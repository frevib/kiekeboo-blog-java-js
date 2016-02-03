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

    @Length(min = 1, max = 30)
    @Pattern(regexp = "[\\w\\s]*", message = "not a valid writer")
    private String writer;

//    @NotNull
//    @TitleValidate
    private String title;

//    @NotNull
//    @Size(min=1, max=1500, message = "Too many characters")
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


//    TODO: this is a constructor you dummy!
    public BlogPostDataModel mapRequestToDataModel(BlogPostRequestModel blogPostRequestModel) {
        this.setTitle(blogPostRequestModel.getTitle());
        this.setContents(blogPostRequestModel.getContents());
        this.setDate(new Date());
        // TODO: Fetch writer from session
        this.setWriter("El Escribador");
        return this;
    }

}
