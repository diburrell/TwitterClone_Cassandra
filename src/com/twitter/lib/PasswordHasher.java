package com.twitter.lib;

/*
 *Used to hash/unhash password stored in db for security 
 */


public class PasswordHasher {
	
	public static String byteArrayToString(byte[] b) {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
		}
}
