package com.kiekeboo.app.model;

import javax.persistence.*;

@Entity
@Table(name = "comments", schema = "kb_database", catalog = "")
public class CommentsDataModel {
    private int commentId;
    private String author;
    private String commentContents;
    private byte enabled;

    @Id
    @Column(name = "id_comment")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int idComment) {
        this.commentId = idComment;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "comment_contents")
    public String getCommentContents() {
        return commentContents;
    }

    public void setCommentContents(String commentContents) {
        this.commentContents = commentContents;
    }

    @Basic
    @Column(name = "enabled")
    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsDataModel that = (CommentsDataModel) o;

        if (commentId != that.commentId) return false;
        if (enabled != that.enabled) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (commentContents != null ? !commentContents.equals(that.commentContents) : that.commentContents != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (commentContents != null ? commentContents.hashCode() : 0);
        result = 31 * result + (int) enabled;
        return result;
    }
}
