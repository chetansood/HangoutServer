package com.example.supersaiyans.hangout.model;

import java.io.Serializable;

public class Comment implements Serializable{
	
	private int commentID;
	private int userID;
	private int eventID;
	private String commentText;
	
	public Comment(int commentID, int userID, int eventID,String commentText) {
		super();
		this.commentID = commentID;
		this.commentText = commentText;
		this.userID=userID;
		this.eventID=eventID;
	}
	
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

}
