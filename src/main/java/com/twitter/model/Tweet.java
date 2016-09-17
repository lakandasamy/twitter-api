package com.twitter.model;

/**
 * Holds the Tweet information like who tweetId, message,etc.,
 * 
 **/
public class Tweet extends BaseObject {

	/**
	 * Unique id for the serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Username to hold the name of the user.
	 */
	private String userName;
	
	/**
	 * Tweet message from the user. .
	 */
	private String message;
	
	/**
	 * Get the username.
	 * 
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Set the username.
	 * 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * get the Tweet message.
	 * 
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Set the Tweet Message.
	 * 
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
