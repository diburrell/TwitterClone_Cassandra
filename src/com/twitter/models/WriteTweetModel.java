package com.twitter.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.twitter.stores.TweetStore;

public class WriteTweetModel {
	Cluster cluster;

	public WriteTweetModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public void storeTweet(TweetStore newTweet) {

		Session session = cluster.connect("TwitterDB");
		
		PreparedStatement statement = session.prepare("INSERT INTO Tweets (id, content, user, when) Values (now(),'"+newTweet.getTweet()+"',"+newTweet.getUserID()+",dateof(now()))");
		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);

		session.close();
		System.out.println("Tweet written!");
	}

}
