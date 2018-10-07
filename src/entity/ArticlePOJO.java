package entity;

import java.sql.Timestamp;

public class ArticlePOJO {
    private int articleID;
    private int authorID;
    private String title;
    private String articleContent;
    private Timestamp publicationTime; //check this is the right data type?
    private String picPath;

    public int getArticleID(){return articleID;}

    public void setArticleID(int articleID){this.articleID=articleID;}

    public int getAuthorID(){return authorID;}

    public void setAuthorID(int authorID){this.authorID=authorID;}

    public String getTitle(){return title;}

    public void setTitle(String title){this.title=title;}

    public String getArticleContent(){return articleContent;}

    public void setArticleContent(String articleContent){this.articleContent=articleContent;}

    public Timestamp getPublicationTime(){return publicationTime;}

    public void setPublicationTime(Timestamp publicationTime) {this.publicationTime=publicationTime;}

    public String getPicPath() {return picPath;}

    public void setPicPath(String picPath){this.picPath=picPath;}



}
