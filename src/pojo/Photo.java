package pojo;

public class Photo {
	
	private int photoId;
	private int userId;
	private String url;
	public Photo(){
		
	}
	
	public Photo(int photoId){
		super();
		this.photoId=photoId;
	}
	
	public Photo(int userId, String url){
		super();
		this.userId=userId;
		this.url=url;	
	}
	
	public Photo(int photoId, int userId, String url){
		super();
		this.photoId=photoId;
		this.userId=userId;
		this.url=url;	
	}
	
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
