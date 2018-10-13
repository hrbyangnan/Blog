package pojo;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    /**
     *
     */
    private int articleId;
    private int userId;
    private String articleName;
    private String articleContent;
    private Date pubTime;
    private String picPath;

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Article(int articleId, int userId, String articleName, String articleContent, Date pubTime, String realName) {
        this.articleId = articleId;
        this.userId = userId;
        this.articleName = articleName;
        this.articleContent = articleContent;
        this.pubTime = pubTime;
        this.realName = realName;
    }

    private String realName;

    public Article(){super();}

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
                   String articleContent, Date pubTime) {
        super();
        this.articleId = articleId;
        this.userId = userId;
        this.articleName = articleName;
        this.articleContent = articleContent;
        this.pubTime = pubTime;
    }

    //This constructor adds realName but does not include everything for posting an article
    public Article(int userId, String articleName, String articleContent, String realName){
        this.userId=userId;
        this.articleName=articleName;
        this.articleContent=articleContent;
        this.realName=realName;
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

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getRealName(){return realName;}

    public void setRealName(String realName){this.realName = realName;}

}