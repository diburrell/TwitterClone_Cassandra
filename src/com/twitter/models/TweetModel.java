package com.twitter.models;

import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.twitter.stores.TweetStore;

public class TweetModel {
	Cluster cluster;

	public TweetModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public TweetStore getTweet(UUID tweet) {
		Session session = cluster.connect("TwitterDB");

			PreparedStatement statement = session
					.prepare("SELECT * from tweets where id = " + tweet+" ALLOW FILTERING");
			BoundStatement boundStatement = new BoundStatement(statement);
			ResultSet rs = session.execute(boundStatement);

			if (rs.isExhausted()) {
				System.out.println("No Tweets found");
				
				session.close();
				
				return null;
			} else {
				
					Row row = rs.one();
				
					TweetStore ts = new TweetStore();
					ts.setTweet(row.getString("content"));
					ts.setTweetID(row.getUUID("id"));
					ts.setWhen(row.getDate("when"));
					ts.setUserID(row.getUUID("user"));
					ts.setUser(userName(row.getUUID("user"), session));		
					
					session.close();
					
					return ts;
				}
		}
								
	

	private String userName(UUID id, Session session) {
		PreparedStatement statement = session
				.prepare("SELECT username from users where id =" + id);
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);

		Row res = rs.one();
		return res.getString("username");
	}
}
