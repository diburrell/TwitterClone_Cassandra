package com.twitter.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.twitter.stores.UserStore;

public class EditUserModel {

	Cluster cluster;

	public EditUserModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public void editUser(UserStore editedUser) {

		Session session = cluster.connect("TwitterDB");

		PreparedStatement statement = session
				.prepare("UPDATE users SET firstname = '"
						+ editedUser.getFirst() + "', lastname = '"
						+ editedUser.getLast() + "', gender = '"
						+ editedUser.getSex() + "', bio = '"
						+ editedUser.getBio() + "', birthdate ='"
						+ editedUser.getBirthday() + "' WHERE email = '"
						+ editedUser.getEmail() + "'");

		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);

		session.close();

	}
}
