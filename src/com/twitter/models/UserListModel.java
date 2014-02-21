package com.twitter.models;

import java.util.LinkedList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class UserListModel {

	Cluster cluster;

	public UserListModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public LinkedList<String> getUsers() {
		LinkedList<String> userList = new LinkedList<String>();

		Session session = cluster.connect("TwitterDB");

		PreparedStatement statement = session
				.prepare("Select username from users");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		
		if(rs.isExhausted()){
			System.out.println("No Users found");
		}
		else
		{
			for(Row row : rs)
			{
				userList.add(row.getString("username"));
			}
		}
		
		session.close();
		
		return userList;
	}
}
