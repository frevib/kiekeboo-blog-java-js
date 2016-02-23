package com.kiekeboo.app.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kiekeboo.app.model.interfaces.BlogPostInterface;
import com.kiekeboo.app.services.CustomDateSerializerService;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class BlogPostDataModel implements BlogPostInterface {

    private int blogpostId;
    private Date date;
    private int writer;
    private String title;
    private String contents;
    private boolean enabled;
    private boolean commentsEnabled;

    public boolean getCommentsEnabled() {
        return commentsEnabled;
    }

    public void setCommentsEnabled(boolean commentsEnabled) {
        this.commentsEnabled = commentsEnabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public int getWriterId() {
        return writer;
    }

    public void setWriterId(int writer) {
        this.writer = writer;
    }

    public BlogPostDataModel mapRequestToDataModel(BlogPostRequestModel blogPostRequestModel) {
        this.setTitle(blogPostRequestModel.getTitle());
        this.setContents(blogPostRequestModel.getContents());
        this.setDate(new Date());
        // TODO: Fetch writer from session
        this.setWriterId(1);
        this.setEnabled(true);
        this.setCommentsEnabled(true);
        return this;
    }

}
