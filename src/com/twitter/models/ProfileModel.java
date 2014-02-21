package com.twitter.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import com.twitter.stores.UserStore;

public class ProfileModel {

	Cluster cluster;

	public ProfileModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public UserStore getUser(String un) {
		UserStore currUser = new UserStore();
		Row details = null;

		Session session = cluster.connect("TwitterDB");

		PreparedStatement statement = session
				.prepare("SELECT * from users WHERE username='" + un + "'");
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
				currUser.setFirst(details.getString("firstname"));
				currUser.setLast(details.getString("lastname"));
				currUser.setSex(details.getString("gender"));
				currUser.setSex(details.getString("gender"));
				currUser.setBio(details.getString("bio"));
			}
		}
		session.close();

		return currUser;
	}
}
