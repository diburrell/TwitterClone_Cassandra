package com.twitter.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.twitter.stores.UserStore;


public class NewUserModel {
	Cluster cluster;

	public NewUserModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public UserStore storeUser(UserStore newUser, String pw) {
		
		Session session = cluster.connect("TwitterDB");

		PreparedStatement statement = session
				.prepare("INSERT INTO Users (id, email, username, password, type) VALUES (now(), '"
						+ newUser.getEmail()
						+ "','"
						+ newUser.getName()
						+ "','" + pw + "','user')");
		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);

		PreparedStatement getID = session
				.prepare("Select id from users where email ='"
						+ newUser.getEmail() + "'");
		BoundStatement boundStatement2 = new BoundStatement(getID);
		ResultSet rs = session.execute(boundStatement2);

		Row value = rs.one();


	System.out.println(value.getUUID("id"));
		newUser.setID(value.getUUID("id"));

		AddFollowerModel afm = new AddFollowerModel();
		afm.setCluster(cluster);
		
		
	    newUser.setFollowing(afm.newFollower(newUser, newUser));
		
		session.close();

		return newUser;
	}

}
