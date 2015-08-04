package com.example.supersaiyans.hangout.model;

import java.io.Serializable;

public class Comment implements Serializable{
	
	private int commentID;
	private int userID;
	private int eventID;
	private String commentText;
	static final long serialVersionUID = 44L;
	
	public Comment(int commentID, int userID, int eventID,String commentText) {
		//super();
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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	
	/*public String toString() {
        return "commentId: " + commentID + ", userId: " + userID + ", eventID: " + eventID + ", comment: " + commentText;
    }*/

}
