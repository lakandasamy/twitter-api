package com.twitter.dao;

import java.util.Collection;
import java.util.Date;

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

@ContextConfiguration({"classpath:/daoContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TweetDaoTest {
	
	@Autowired
	TweetDao tweetDao;
	
	@Autowired
	UserFollowerDao userFollowerDao;

	public  void setUp(){
		Tweet tweet = new Tweet();
		tweet.setUserId(44);
		tweet.setMessage("Tony Hawk Joins Lineup of QuickBooks Connect Speakers");
		tweet.setCreatedDate(new Date());
		tweetDao.insert(tweet);
		
		tweet.setUserId(44);
		tweet.setMessage("Michael Phelps, Simone Biles Headlining QuickBooks Connect in San Jose");
		tweet.setCreatedDate(new Date());
		tweetDao.insert(tweet);
		
		UserFollower userFollower = new UserFollower();
		userFollower.setUserId(44);
		userFollower.setFollowerId(45);
		userFollower.setCreatedDate(new Date());
		userFollowerDao.insert(userFollower);
	}

	/**
	 * Test the TweetDao to get the lastest tweets for the user. 
	 * This user should have > 1 tweets
	 */
	@Test
	public void testGetLatestTweetsForUser() {
		setUp();
		Collection<Tweet> tweets = tweetDao.selectLatestByUsers(45);
		Assert.assertTrue(tweets.size() > 1);
		cleanup();
	}
	
	/**
	 * Test the TweetDao to get the tweets for the user. 
	 * This user does not have any tweets so, it should return 0 tweets.
	 */
	@Test
	public void testGetLatestTweetsForUserWithNoTweets() {
		Collection<Tweet> tweets = tweetDao.selectLatestByUsers(30);
		Assert.assertTrue(tweets.size() == 0);
	}

	/**
	 * Clean the data that set for the tests to run.
	 */
	private void cleanup() {
		tweetDao.deleteByUserId(44);
		userFollowerDao.deleteByFollowerId(45);
	}
}
