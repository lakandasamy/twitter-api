package com.twitter.model;

import org.junit.Assert;
import org.junit.Test;

public class UserFollowerTest {

	@Test
	public void test() {
		UserFollower userFollower = new UserFollower();
		userFollower.setUserId(40);
		Assert.assertTrue(40 == userFollower.getUserId());
		
		userFollower.setFollowerId(45);
		Assert.assertTrue(45 == userFollower.getFollowerId());
	}
}
