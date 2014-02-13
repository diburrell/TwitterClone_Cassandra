package com.twitter.stores;

public class UserStore {
	int ID;
	String email;
	String userName;

	public int getID() {
		return ID;
	}

	public String getEmail() {
		return email;
	}
	
	public String getName()
	{
	  return userName;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String userName) {
		this.userName = userName;
	}

}
