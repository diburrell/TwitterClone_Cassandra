package com.twitter.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import com.twitter.lib.*;
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
		} else {
			{
				
				details = rs.one();
				currUser.setID(details.getInt("id"));
				currUser.setEmail(details.getString("email"));
				currUser.setName(details.getString("name"));			}
		}
		session.close();
//System.out.println("Does "+details.getString("password")+" = "+pw);

		if (pw.equals(details.getString("password"))) {
//System.out.println("Yes");
			return currUser;
		} else {
//System.out.println("No");
			return null;
		}
	}
}
