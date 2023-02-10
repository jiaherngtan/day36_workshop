package com.vttp2022.day36.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {

    private String commentId;
    private String user;
    private Integer rating;
    private String text;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment [commentId=" + commentId + ", user=" + user + ", rating=" + rating + ", text=" + text + "]";
    }

    public static Comment create(Document d) {
        Comment comment = new Comment();
        comment.setCommentId(d.getString("c_id"));
        comment.setUser(d.getString("user"));
        comment.setRating(d.getInteger("rating"));
        comment.setText(d.getString("c_text"));
        return comment;
    }

    public JsonObject toCommentSummary() {
        return Json.createObjectBuilder()
                .add("commentId", commentId)
                .add("user", user)
                .add("rating", rating)
                .add("text", text)
                .build();
    }

}
