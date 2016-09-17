package com.twitter.dao;


import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.twitter.dao.UserFollowerDao;
import com.twitter.model.UserFollower;

@ContextConfiguration({"classpath:/daoContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserFollowerDaoTest {
	
	@Autowired
	UserFollowerDao userFollowerDao;

	public  void setUp(){
		UserFollower userFollower = new UserFollower();
		userFollower.setUserId(31);
		userFollower.setFollowerId(30);
		userFollower.setCreatedDate(new Date());
		userFollowerDao.insert(userFollower);
	}

	/**
	 * Test the User Follower testSelectByFollower.
	 */
	@Test
	public void testSelectByFollower() {
		setUp();
		Collection<UserFollower> userFollowers = userFollowerDao.selectByFollowerId(30);
		Assert.assertTrue(userFollowers.size() > 0);
		cleanup();
		userFollowers = userFollowerDao.selectByFollowerId(30);
		Assert.assertTrue(userFollowers.size() == 0);
	}

	/**
	 * Clean the data that set for the tests to run.
	 */
	private void cleanup() {
		userFollowerDao.deleteByFollowerId(30);
	}

}
