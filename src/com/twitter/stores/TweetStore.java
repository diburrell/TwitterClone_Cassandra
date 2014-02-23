package com.twitter.stores;

import java.util.Date;
import java.util.UUID;

public class TweetStore implements Comparable<TweetStore> {
	String Tweet;
	String UserName;
	UUID TweetID;
	UUID UserID;
	Date when;

	public String getTweet() {
		return Tweet;
	}

	public String getUser() {
		return UserName;
	}

	public UUID getTweetID() {
		return TweetID;
	}

	public UUID getUserID() {
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

	public void setTweetID(UUID TweetID) {
		this.TweetID = TweetID;
	}

	public void setUserID(UUID UserID) {
		this.UserID = UserID;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	@Override
	public int compareTo(TweetStore o) {
		Date comparedDate = o.when;
		if (this.when.compareTo(comparedDate) < 0) {
			System.out.println("Move up");
			return 1;
		} else if (this.when.compareTo(comparedDate) == 0) {
			return 0;
		} else {
			System.out.println("Move down");
			return -1;
		}
	}

}
