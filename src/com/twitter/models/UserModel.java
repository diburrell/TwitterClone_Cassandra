package com.twitter.models;

import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.twitter.stores.UserStore;

public class UserModel {

	Cluster cluster;

	public UserModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public UserStore getUser(String em, String pw) {
		UserStore currUser = new UserStore();
		Row details = null;

		Session session = cluster.connect("TwitterDB");

		PreparedStatement statement = session
				.prepare("SELECT * from users WHERE email='" + em + "'");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No user found");
			return null;
		} else {
			{
				
				details = rs.one();
				currUser.setID(details.getUUID("id"));
				currUser.setEmail(details.getString("email"));
				currUser.setName(details.getString("username"));
				currUser.setBio(details.getString("bio"));
				currUser.setFirst(details.getString("firstname"));
				currUser.setLast(details.getString("lastname"));
				currUser.setSex(details.getString("gender"));
				currUser.setBirthday(details.getString("birthdate"));
				
				//people user is following
				currUser.setFollowing(details.getSet("following", UUID.class));		
				currUser.setFollowCount(details.getInt("followCount"));
				//people following the user
				currUser.setFollowers(details.getSet("followers", UUID.class));
				currUser.setFollowerCount(details.getInt("followerCount"));
			}
						
		}
		session.close();
		if (pw.equals(details.getString("password"))) {
			return currUser;
		} else {
			return null;
		}
	}
}
