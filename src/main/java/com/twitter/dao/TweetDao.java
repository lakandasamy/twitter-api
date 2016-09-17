package com.twitter.dao;

import java.util.ArrayList;
import java.util.Collection;

import com.twitter.model.Tweet;

/**
 * 
 * @author lkandasamy
 *
 * This Interface defines the skeleton for the Tweet Dao
 */

public interface TweetDao extends Dao<Tweet> {
	
	/**
	 * Select latest 100 Tweets from the Users that the UserId follows.
	 * 
	 * @param userId follows the other users
	 * 
	 * @return Collection of latest Tweets from the other users.
	 * 
	 */
	Collection<Tweet> selectLatestByUsers(final Integer userId);

	/**
	 * Select all the Tweets from the Tweet table.
	 * 
	 * @return Collection of Tweets.
	 * 
	 */
	Collection<Tweet> selectAll();
	
	/**
	 * Delete the tweets created by a user. 
	 * 
	 * @param userId
	 *            delete the tweets for this user.
	 *
	 * @return 
	 */
	void deleteByUserId(Integer userId) ;

}
