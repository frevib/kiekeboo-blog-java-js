package com.kiekeboo.app.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kiekeboo.app.services.CustomDateSerializerService;
import com.kiekeboo.app.validation.BlogPostValidate;
import com.kiekeboo.app.validation.TitleValidate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class BlogPost {

    private int id;

    @TitleValidate
    private String title;

    @Size(min=1, max=1500)
    private String contents;

    private Date date;

    @Length(max = 30)
    @Pattern(regexp = "\\w*", message = "not a valid writer")
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
}
