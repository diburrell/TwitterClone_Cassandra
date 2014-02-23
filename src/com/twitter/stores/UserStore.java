package com.twitter.stores;

import java.util.Set;
import java.util.UUID;

public class UserStore implements Comparable<UserStore> {
	UUID ID;
	
	Set<UUID> following;
    Set <UUID> followers;
    
	String email;
	String userName;
	String sex;
	String fname;
	String lname;
	String bio;
	String birthday;
	
	int followCount;
	int followerCount;
	

	
	public UUID getID() {
		return ID;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return userName;
	}

	public String getSex() {
		return sex;
	}

	public String getFirst() {
		return fname;
	}

	public String getLast() {
		return lname;
	}

	public String getBio() {
		return bio;
	}

	public int getFollowCount() {
		return followCount;
	}

	public Set<UUID> getFollowing() {
		return following;
	}

	public int getFollowerCount() {
		return followerCount;
	}

	public Set<UUID> getFollowers() {
		return followers;
	}
	
	public String getBirthday()
	{
		return birthday;
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

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setFirst(String fname) {
		this.fname = fname;
	}

	public void setLast(String lname) {
		this.lname = lname;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setFollowing(Set<UUID> following) {
		this.following = following;
	}

	public void addFollwoing(UUID newFollowing) {
		following.add(newFollowing);
	}
	
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}
	
	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}

	public void setFollowers(Set<UUID> followers) {
		this.followers = followers;
	}

	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}
	
	@Override
	public int compareTo(UserStore o) {
		String comparedName = o.userName;
		if (this.userName.compareTo(comparedName) < 0) {
			return 1;
		} else if (this.userName.compareTo(comparedName) == 0) {
			return 0;
		} else {
			return -1;
		}
	}
}
