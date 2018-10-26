package pojo;

import java.io.Serializable;

public class CommentOnComment implements Serializable {
    private int commentId;
    private String commentContent;
    private int fatherCommentId;
    private int userId;
    private String userName;
    private int visible;

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public CommentOnComment(int commentId, int fatherCommentId, String commentContent, int userId, String userName) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.fatherCommentId = fatherCommentId;
        this.userId = userId;
        this.userName = userName;
    }


    public CommentOnComment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(int fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}