package com.twitter.service;


import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.twitter.dao.TweetDao;
import com.twitter.dao.UserFollowerDao;
import com.twitter.model.Tweet;
import com.twitter.model.UserFollower;
import com.twitter.service.TweetService;

@ContextConfiguration({"classpath:/daoContext.xml", "classpath:testApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TweetServiceTest {
	
	@Autowired
	TweetDao tweetDao;
	
	@Autowired
	UserFollowerDao userFollowerDao;
	
	@Autowired
	TweetService tweetService;
	
	public  void setUp(){
		Tweet tweet = new Tweet();
		tweet.setUserId(20);
		tweet.setMessage("H&R Block Taxed by Intuit's Do-It-Yourself Business Model");
		tweet.setCreatedDate(new Date());
		tweetDao.insert(tweet);
		
		tweet.setUserId(20);
		tweet.setMessage("Michael Phelps, Simone Biles Headlining QuickBooks Connect in San Jose");
		tweet.setCreatedDate(new Date());
		tweetDao.insert(tweet);
		
		UserFollower userFollower = new UserFollower();
		userFollower.setUserId(20);
		userFollower.setFollowerId(25);
		userFollower.setCreatedDate(new Date());
		userFollowerDao.insert(userFollower);
	}

	/**
	 * Test the service to get the lastest tweets for the user. 
	 * This user should have > 1 tweets
	 */
	@Test
	public void testGetLatestTweetsForUser() {
		setUp();
		List<Tweet> tweets = tweetService.getLatestTweetsForUser(25);
		Assert.assertTrue(tweets.size() > 0);
		
		cleanup();
	}
	
	/**
	 * Test the service to get the lastest tweets for the user. 
	 * This user doesnot have tweets, so should return 0 tweets
	 */
	@Test
	public void testGetLatestTweetsForUserWithNoTweets() {
		List<Tweet> tweets = tweetService.getLatestTweetsForUser(30);
		Assert.assertTrue(tweets.size() == 0);
	}

	/**
	 * Clean the data that set for the tests to run.
	 */
	private void cleanup() {
		tweetDao.deleteByUserId(20);
		userFollowerDao.deleteByFollowerId(25);
	}
}
