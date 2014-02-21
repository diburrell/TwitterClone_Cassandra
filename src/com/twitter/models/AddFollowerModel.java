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

	public Set<UUID> newFollower(UserStore user, UUID toFollow) {
		Session session = cluster.connect("TwitterDB");

		PreparedStatement addFollowing = session
				.prepare("UPDATE users SET following = following + {"
						+ toFollow + "} WHERE email = '" + user.getEmail() + "'");

		BoundStatement boundStatement = new BoundStatement(addFollowing);
		session.execute(boundStatement);
		
		
		PreparedStatement getFollowing =  session
				.prepare("Select following from users WHERE email = '" + user.getEmail() + "'");
		boundStatement = new BoundStatement(getFollowing);
		ResultSet rs = session.execute(boundStatement);
		
		Row result = rs.one();
		Set<UUID> newFollowing = result.getSet("following", UUID.class); 
		
		session.close();
		
		return newFollowing;
	}
}
