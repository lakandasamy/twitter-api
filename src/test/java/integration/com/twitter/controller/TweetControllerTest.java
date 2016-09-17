package integration.com.twitter.controller;

import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.twitter.controller.v1.TweetController;
import com.twitter.dao.TweetDao;
import com.twitter.dao.UserFollowerDao;
import com.twitter.model.Tweet;
import com.twitter.model.UserFollower;

@ContextConfiguration({ "classpath:/daoContext.xml",
		"classpath:testApplicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TweetControllerTest {

	@Autowired
	TweetDao tweetDao;

	@Autowired
	UserFollowerDao userFollowerDao;

	@Autowired
	TweetController tweetController;

	public void setUp() {
		Tweet tweet = new Tweet();
		tweet.setUserId(46);
		tweet.setMessage("H&R Block Taxed by Intuit's Do-It-Yourself Business Model");
		tweet.setCreatedDate(new Date());
		tweetDao.insert(tweet);

		tweet.setUserId(46);
		tweet.setMessage("Intuit to Present at Citi Global Technology Conference. ");
		tweet.setCreatedDate(new Date());
		tweetDao.insert(tweet);

		UserFollower userFollower = new UserFollower();
		userFollower.setUserId(46);
		userFollower.setFollowerId(47);
		userFollower.setCreatedDate(new Date());
		userFollowerDao.insert(userFollower);
	}

	/**
	 * Test the controller with a valid UserId. It should get the Status code 200 
	 * with the tweets.
	 */
	@Test
	public void testGetLatestTweetsForUserWithValidResponse() {
		setUp();
		ResponseEntity<String> responseEntity = tweetController
				.getLatestTweetsForUser("47");
		Assert.assertTrue(responseEntity.getBody().length() > 1);
		Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		
		cleanup();
	}
	
	/**
	 * Test the controller with No UserId. It should get the Bad request and
	 *  the Status code 400 
	 * 
	 */
	@Test
	public void testGetLatestTweetsForUserWithNoUserId() {
		setUp();
		ResponseEntity<String> responseEntity = tweetController
				.getLatestTweetsForUser(null);
		Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
		cleanup();
	}
	
	/**
	 * Test the controller with the User who doesnot have any tweets. 
	 * It should get the No Content response and
	 *  the Status code 204 
	 * 
	 */
	@Test
	public void testGetLatestTweetsForUserWithNoTweet() {
		ResponseEntity<String> responseEntity = tweetController
				.getLatestTweetsForUser("51");
		Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.NO_CONTENT);
	}

	/**
	 * Clean the data that set for the tests to run.
	 */
	private void cleanup() {
		tweetDao.deleteByUserId(46);
		userFollowerDao.deleteByFollowerId(47);
	}
}
