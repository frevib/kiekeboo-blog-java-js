package com.kiekeboo.app.model;

import com.kiekeboo.app.validation.TitleValidate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class BlogPostModel {
//      TODO: is this OK? null != empty
    @NotNull
    @TitleValidate
    private String title;

    @NotNull
    @Size(min=1, max=1500, message = "Too many characters")
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
}
