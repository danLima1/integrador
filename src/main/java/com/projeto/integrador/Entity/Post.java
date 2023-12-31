package com.projeto.integrador.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;



@Entity(name="Post")

public class Post {

    @Id
    @GeneratedValue
    private int Id;
    private String postId;
    public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPostPath() {
		return postPath;
	}
	public void setPostPath(String postPath) {
		this.postPath = postPath;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	private String userId;
    private String userName;
    private String postPath;
    private String timeStamp;
    private int likeCount;

}