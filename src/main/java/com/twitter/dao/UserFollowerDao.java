package com.twitter.dao;

import java.util.Collection;

import com.twitter.model.UserFollower;

/**
 * 
 * @author lkandasamy
 *
 *  This Interface defines the UserFollower Dao
 */

public interface UserFollowerDao extends Dao<UserFollower> {

	/**
	 * Select all Users and the followers for the users from the table
	 * 
	 * @param selectAllQuery
	 *            the query used to select all tweets from the table
	 * @return the collection of tweet class instances generated from the result
	 *         set
	 */
	Collection<UserFollower> selectAll();

	/**
	 * Delete the User follower record from the User_Follower table
	 * 
	 * @param userId
	 *            userId is the follower of the other users.
	 *
	 * @return
	 */
	Integer deleteByFollowerId(Integer userId);
	
	/**
	 * Select all Users and the followers for the followers from the table
	 * 
	 * @param userId
	 *             FollowerId to find the users and followers records.
	 * @return the collection of UserFollower class instances generated from the result
	 *         set
	 */
	Collection<UserFollower> selectByFollowerId(final Integer userId) ;

}
