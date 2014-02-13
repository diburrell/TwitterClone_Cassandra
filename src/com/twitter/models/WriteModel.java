package com.twitter.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.twitter.stores.TweetStore;

public class WriteModel {
	Cluster cluster;

	public WriteModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public void storeTweet(TweetStore newTweet) {

		Session session = cluster.connect("TwitterDB");
		
		session.execute("INSERT INTO Tweets (UserName, Tweet) Values ('"+newTweet.getUser()+"','"+newTweet.getTweet()+ "')");
		System.out.println("Tweet written!");
	}

}
