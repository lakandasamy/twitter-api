package com.twitter.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twitter.dao.TweetDao;
import com.twitter.model.Tweet;

/**
 * 
 * @author lkandasamy
 *
 *         This service performs the actions related to Tweet. E.g., CRUD
 *         operations per calling the Dao.
 * 
 */

@Component
public class TweetService {

	@Autowired
	TweetDao tweetDao;
	
	Logger logger = Logger.getLogger(TweetService.class);

	/**
	 * Get the latest 100 posts from the users that the current user follows.
	 * 
	 * @param Integer
	 *            required UserId.
	 * @return List of 100 or less latest Tweets from the DB
	 *
	 **/

	public List<Tweet> getLatestTweetsForUser(Integer userId) {

		List<Tweet> tweets = (ArrayList<Tweet>) tweetDao
				.selectLatestByUsers(userId);
				
		logger.debug("Number of tweets for user"+userId +" = " + tweets.size());
		return tweets;
	}

	public TweetDao getTweetDao() {
		return tweetDao;
	}

	public void setTweetDao(TweetDao tweetDao) {
		this.tweetDao = tweetDao;
	}

}
