package pojo;

import java.io.Serializable;

public class Article implements Serializable {
    /**
	 * 
	 */
	private int articleId;
    private int userId;
    private String articleName;
    private String articleContent;
    private String pubTime;

    public Article(){
	  
    }


	public Article(int userId, String articleName, String articleContent,
                   String pubTime) {
		super();
		this.userId = userId;
		this.articleName = articleName;
		this.articleContent = articleContent;
		this.pubTime = pubTime;
	}

	public Article(int userId, String articleName, String articleContent) {
		super();
		this.userId = userId;
		this.articleName = articleName;
		this.articleContent = articleContent;
	}


	public Article(int articleId) {
		super();
		this.articleId = articleId;
	}

 

	public Article(int articleId, String articleContent) {
		super();
		this.articleId = articleId;
		this.articleContent = articleContent;
	}



	public Article(int articleId, int userId, String articleName,
                   String articleContent) {
		super();
		this.articleId = articleId;
		this.userId = userId;
		this.articleName = articleName;
		this.articleContent = articleContent;
	}


	public Article(int articleId, int userId, String articleName,
                   String articleContent, String pubTime) {
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

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
  
  
  
}  