package pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Article implements Serializable {
    /**
     *
     */
    private int articleId;
    private int userId;
    private String articleName;
    private String articleContent;
    private LocalDateTime pubTime;

    public Article(int articleId, String articleName, String articleContent) {
        this.articleName = articleName;
        this.articleContent = articleContent;
        this.articleId = articleId;

    }

    public Article (String articleName, int userId, String articleContent){
        this.userId = userId;
        this.articleName = articleName;
        this.articleContent = articleContent;
//        this.pubTime = pubTime;

    }

    public Article(int articleId, int userId, String articleName,
                   String articleContent, LocalDateTime pubTime) {
        super();
        this.articleId = articleId;
        this.userId = userId;
        this.articleName = articleName;
        this.articleContent = articleContent;
        this.pubTime = pubTime;
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

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public LocalDateTime getPubTime() {
        return pubTime;
    }

    public void setPubTime(LocalDateTime pubTime) {
        this.pubTime = pubTime;
    }


}  