package com.twitter.models;

import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.twitter.stores.UserStore;

public class AddFollowerModel {
	Cluster cluster;

	public AddFollowerModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public Set<UUID> newFollower(UserStore user, UserStore profile) {
		Session session = cluster.connect("TwitterDB");

		PreparedStatement addFollowing = session
				.prepare("UPDATE users SET following = following + {"
						+ profile.getID() + "} WHERE email = '" + user.getEmail()
						+ "'");
		BoundStatement boundStatement = new BoundStatement(addFollowing);
		session.execute(boundStatement);

		PreparedStatement getFollowing = session
				.prepare("Select following from users WHERE email = '"
						+ user.getEmail() + "'");
		boundStatement = new BoundStatement(getFollowing);
		ResultSet rs = session.execute(boundStatement);
		
		Row result = rs.one();
		Set<UUID> userIsFollowing = result.getSet("following", UUID.class);

		PreparedStatement updateFollowCount = session
				.prepare("UPDATE users SET followcount = "
						+ (userIsFollowing.size() - 1) + "WHERE email = '"
						+ user.getEmail() + "'");

		boundStatement = new BoundStatement(updateFollowCount);
		session.execute(boundStatement);

		
		
		
		if(!profile.equals(user))
		{
		PreparedStatement updateFollowing = session
				.prepare("Update users SET followers = followers + {"+user.getID()+"} "
				+ "where email = '"+profile.getEmail()+"'");
		
		boundStatement = new BoundStatement(updateFollowing);
		session.execute(boundStatement);
		

		PreparedStatement getFollowers = session
				.prepare("Select following from users WHERE email = '"
						+ user.getEmail() + "'");
		boundStatement = new BoundStatement(getFollowers);
		ResultSet rs2 = session.execute(boundStatement);
		
		Row result2 = rs2.one();
		Set<UUID> profileFollowers = result2.getSet("following", UUID.class);

		PreparedStatement updateFollowerCount = session
				.prepare("UPDATE users SET followercount = "
						+ (profileFollowers.size() - 1) + "WHERE email = '"
						+ profile.getEmail() + "'");

		boundStatement = new BoundStatement(updateFollowerCount);
		session.execute(boundStatement);
		
		
		}
		
		session.close();

		return userIsFollowing;
	}
}
