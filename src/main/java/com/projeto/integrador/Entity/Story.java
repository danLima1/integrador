package com.projeto.integrador.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity(name="Story")

public class Story {

    @Id
    @GeneratedValue
    private int id;

    private String statusId;
    private String userId;
    private String path;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	private String userName;
    private String timeStamp;

    public Story(int id, String statusId, String userId, String path, String timeStamp) {
        super();
        this.id = id;
        this.statusId = statusId;
        this.userId = userId;
        this.path = path;
        this.timeStamp = timeStamp;
    }

}