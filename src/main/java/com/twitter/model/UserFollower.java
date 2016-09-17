package com.twitter.model;

public class UserFollower extends BaseObject {

	/**
	 * serialVersionUID: UniqueId 
	 */
	private static final long serialVersionUID = 1675767L;
	
	/**
	 * FollowerId: Follower of the other users.
	 */
	private Integer followerId;
	
	
	/**
	 * Getter for the FollowerId.
	 * 
	 */
	public Integer getFollowerId() {
		return followerId;
	}

	/**
	 * Setter of the FollowerId.
	 * 
	 */
	public void setFollowerId(Integer followerId) {
		this.followerId = followerId;
	}

}
