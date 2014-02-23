/*
 *Creates the keyspace and tables needed to run the web project if they are not already available 
 * 
 */



package com.twitter.lib;

import com.datastax.driver.core.*;

public final class KeyspaceCreator {

	public KeyspaceCreator() {

	}

	public static void SetUpKeySpaces(Cluster c) {
		try {
			// Add some keyspaces here
			String createkeyspace = "create keyspace if not exists twitterdb  WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";

			String[] moreCreates = new String[4];

			moreCreates[0] = "CREATE TABLE if not exists tweets("
					+ "id timeuuid," + "content varchar," + "user timeuuid,"
					+ "when timestamp," + "PRIMARY KEY((user),id))"
					+ "With CLUSTERING ORDER BY (id DESC);";
			moreCreates[1] = "CREATE TABLE if not exists users ("
					+ "email varchar," + "id timeuuid," + "username varchar,"
					+ "firstname varchar," + "lastname varchar,"
					+ "gender varchar," + "birthdate varchar,"
					+ "following set<timeuuid>," + "followcount int,"
					+ "followers set<timeuuid>," + "followercount int,"
					+ "bio varchar," + "password varchar," + "type varchar,"
					+ "PRIMARY KEY(email));";
			moreCreates[2] = "CREATE INDEX usersID ON users (id);";
			moreCreates[3] = "Create INDEX usersName ON users (username);";

			Session session = c.connect();
			try {
				PreparedStatement statement = session.prepare(createkeyspace);
				BoundStatement boundStatement = new BoundStatement(statement);
				ResultSet rs = session.execute(boundStatement);

			} catch (Exception et) {
				System.out.println("Can't create twitterdb " + et);
			}

			for (int i = 0; i < 4; i++) {
				// now add some column families
				session.close();
				session = c.connect("twitterdb");
				System.out.println("" +moreCreates[i]);

				try {
					SimpleStatement cqlQuery = new SimpleStatement(moreCreates[i]);
					session.execute(cqlQuery);
				} catch (Exception et) {
					System.out.println("Can't create tweet table " + et);
				}
				session.close();
			}
			
		} catch (Exception et) {
			System.out.println("Other keyspace or coulm definition error" + et);
		}

	}
}