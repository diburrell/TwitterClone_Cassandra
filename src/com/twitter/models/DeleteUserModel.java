package com.twitter.models;

import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class DeleteUserModel {
	Cluster cluster;

	public DeleteUserModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public void removeUser(UUID userID) {
	
		Session session = cluster.connect("TwitterDB");
		
		PreparedStatement deleteTweets = session.prepare("DELETE from tweets where user = "+userID);
		BoundStatement boundStatement = new BoundStatement(deleteTweets);
		session.execute(boundStatement);
		
		PreparedStatement getEmail = session.prepare("Select email, following, followers From users WHERE id = "+userID);
		boundStatement = new BoundStatement(getEmail);
		ResultSet rs = session.execute(boundStatement);
		
		Row result = rs.one();
		String userEmail = result.getString("email");
		Set<UUID> followers = result.getSet("followers", UUID.class);
		Set<UUID> following  = result.getSet("following", UUID.class); 
		
		//USER FROM EACH USER FOLLOWING
		
		for(UUID f : following)
		{
			PreparedStatement getFEmail = session
					.prepare("SELECT email from users where id =" + f);
			boundStatement = new BoundStatement(getFEmail);
			ResultSet rs2 = session.execute(boundStatement);

			Row res = rs2.one();
			String fEmail = res.getString("email");
			
			
			PreparedStatement removeFromFollowing = session.prepare("UPDATE users SET followers = followers - {"
					+ userID + "} WHERE email = '"+fEmail+"'");

		    boundStatement = new BoundStatement(removeFromFollowing );
			session.execute(boundStatement);
			
			PreparedStatement getFollowers =  session
					.prepare("Select following from users WHERE email = '" + fEmail + "'");
			boundStatement = new BoundStatement(getFollowers);
			ResultSet rs3 = session.execute(boundStatement);
			
			Row followingCount = rs3.one();
			Set<UUID> newFollowing = followingCount.getSet("following", UUID.class); 
			
			PreparedStatement updateFollowCount = session
					.prepare("UPDATE users SET followercount = "
							+ (newFollowing.size() - 1) + "WHERE email = '"
							+ fEmail + "'");
			boundStatement = new BoundStatement(updateFollowCount);
			session.execute(boundStatement);
			
		}
			
		
		
		//REMOVE USER FROM EACH FOLLOWERS SET
		for(UUID f : followers)
		{
			PreparedStatement getFEmail = session
					.prepare("SELECT email from users where id =" + f);
			boundStatement = new BoundStatement(getFEmail);
			ResultSet rs2 = session.execute(boundStatement);

			Row res = rs2.one();
			String fEmail = res.getString("email");
			
			
			PreparedStatement removeFromFollower = session.prepare("UPDATE users SET following = following - {"
					+ userID + "} WHERE email = '"+fEmail+"'");

		    boundStatement = new BoundStatement(removeFromFollower );
			session.execute(boundStatement);
			
			PreparedStatement getFollowing =  session
					.prepare("Select following from users WHERE email = '" + fEmail + "'");
			boundStatement = new BoundStatement(getFollowing);
			ResultSet rs3 = session.execute(boundStatement);
			
			Row followingCount = rs3.one();
			Set<UUID> newFollowing = followingCount.getSet("following", UUID.class); 
			
			PreparedStatement updateFollowCount = session
					.prepare("UPDATE users SET followcount = "
							+ (newFollowing.size() - 1) + "WHERE email = '"
							+ fEmail + "'");
			boundStatement = new BoundStatement(updateFollowCount);
			session.execute(boundStatement);
			
		}
				
		PreparedStatement deleteUser = session.prepare("DELETE From users WHERE email = '"+userEmail+"'");
	    boundStatement = new BoundStatement(deleteUser );
		session.execute(boundStatement);
		
		session.close();
		
	}
}
