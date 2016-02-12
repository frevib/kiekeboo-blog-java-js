package com.kiekeboo.app.model;

import com.kiekeboo.app.model.interfaces.BlogPostInterface;

import java.util.Date;

public class BlogPostViewModel implements BlogPostInterface {

    private int id;
    private String title;
    private String contents;
    private Date date;
    private String writer;

    // TODO: Remove unnecessary fields for view model
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
