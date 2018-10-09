package pojo;

import java.io.Serializable;

public class Comment implements Serializable {

    public Comment(String commentContent, int commentId, int articleId, int userId, String userName) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.articleId = articleId;
        this.userId = userId;
        this.userName = userName;
    }

    private int commentId;
    private String commentContent;
    private int articleId;
    private int userId;
    private String userName;

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

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
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
