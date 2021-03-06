package com.twitter.models;

/*
 * Expects a cassandra columnfamily defined as
 * use keyspace2;
 CREATE TABLE Tweets (
 user varchar,
 interaction_time timeuuid,
 tweet varchar,
 PRIMARY KEY (user,interaction_time)
 ) WITH CLUSTERING ORDER BY (interaction_time DESC);
 * To manually generate a UUID use:
 * http://www.famkruithof.net/uuid/uuidgen
 */

import java.util.LinkedList;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.twitter.stores.TweetStore;

public class TweetListModel {
	Cluster cluster;

	public TweetListModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public LinkedList<TweetStore> getTweets(Set<UUID> users) {
		LinkedList<TweetStore> tweetList = new LinkedList<TweetStore>();
		Session session = cluster.connect("TwitterDB");

	    	for (UUID f : users) {
			PreparedStatement statement = session
					.prepare("SELECT * from tweets where user = " + f);

			BoundStatement boundStatement = new BoundStatement(statement);
			ResultSet rs = session.execute(boundStatement);

			if (rs.isExhausted()) {
				System.out.println("No Tweets found");
			} else {
				for (Row row : rs) {
					TweetStore ts = new TweetStore();
					ts.setTweet(row.getString("content"));
					ts.setTweetID(row.getUUID("id"));
					ts.setWhen(row.getDate("when"));
					ts.setUserID(row.getUUID("user"));
					ts.setUser(userName(row.getUUID("user"), session));		
					tweetList.add(ts);
				}
			}
		}
		
		session.close();
		
		Collections.sort(tweetList);
							
		return tweetList;
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
