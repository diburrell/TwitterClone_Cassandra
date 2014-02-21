package com.twitter.stores;

import java.util.Date;
import java.util.UUID;

public class TweetStore {
	String Tweet;
	String UserName;
	UUID UserID;
	Date when;


	public String getTweet() {
		return Tweet;
	}

	public String getUser() {
		return UserName;
	}
	
	public UUID getID() {
		return UserID;
	}

	public Date getWhen() {
		return when;
	}

	
	public void setTweet(String Tweet) {
		this.Tweet = Tweet;
	}

	public void setUser(String UserName) {
		this.UserName = UserName;
	}

	public void setID(UUID UserID) {
		this.UserID = UserID;
	}

	public void setWhen(Date when) {
		this.when = when;
	}


}
