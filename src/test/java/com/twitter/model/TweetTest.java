package com.twitter.model;

import org.junit.Assert;
import org.junit.Test;

public class TweetTest {

	@Test
	public void test() {
		Tweet tweet = new Tweet();
		tweet.setUserId(40);
		Assert.assertTrue(40 == tweet.getUserId());
		
		tweet.setMessage("Message");
		Assert.assertEquals("Message",  tweet.getMessage());
	}

}
