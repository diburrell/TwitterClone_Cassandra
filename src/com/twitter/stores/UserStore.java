package com.twitter.stores;

import java.util.Set;
import java.util.UUID;

public class UserStore {
	UUID ID;
	String email;
	String userName;
	String sex;
	String fname;
	String lname;
	String bio;
	
	Set<UUID> following;
	
	public UUID getID() {
		return ID;
	}

	public String getEmail() {
		return email;
	}
	
	public String getName()
	{
	  return userName;
	}
	
	public String getSex()
	{
		return sex;
	}
	
	public String getFirst()
	{
		return fname;
	}

	public String getLast()
	{
		return lname;
	}

	public String getBio()
	{
		return bio;
	}
	
	public Set<UUID> getFollowing()
	{
		return following;
	}
	
	public void setID(UUID ID) {
		this.ID = ID;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String userName) {
		this.userName = userName;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public void setFirst(String fname)
	{
		this.fname = fname;
	}
	
	public void setLast(String lname)
	{
		this.lname = lname;
	}
	
	public void setBio(String bio)
	{
		this.bio = bio;
	}
	
	public void setFollowing(Set<UUID> following)
	{
		this.following = following;
	}
	
	public void addFollwoing(UUID newFollowing)
	{
		following.add(newFollowing);
	}
}
