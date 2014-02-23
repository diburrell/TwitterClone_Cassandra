package com.twitter.models;

import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class DeleteTweetModel {
	Cluster cluster;

	public DeleteTweetModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public void removeTweet(UUID tweetID, UUID userID) {
	
		Session session = cluster.connect("TwitterDB");
			
		PreparedStatement statement = session.prepare("DELETE From Tweets WHERE user = "+userID+" and id = "+tweetID);
		BoundStatement boundStatement = new BoundStatement(statement);		
		
		session.execute(boundStatement);
		session.close();
		
	}

}
