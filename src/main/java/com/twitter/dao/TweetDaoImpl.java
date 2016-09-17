package com.twitter.dao;

import java.util.Collection;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.twitter.model.Tweet;

public class TweetDaoImpl extends BaseDao<Tweet> implements TweetDao {

	private static final String INSERT = "insert into Tweet (UserId, Message, Createddate) values (:userId, :message, now())";
	private static final String SELECT_LATEST_BY_USERS = "select t.* from Tweet t, User_Follower ut where t.userId = ut.userId "
			+ " and ut.followerid = :userId"
			+ " order by createddate desc limit 100";
	private static final String SELECT_ALL = "select * from Tweet limit 100";
	private static final String DELETE_BY_USERID = "Delete from Tweet where userId=:userId";

	/**
	 * Default Constructor
	 */
	public TweetDaoImpl() {
		super(Tweet.class);
	}

	/**
	 * Insert the Tweet object into the DB.
	 * 
	 * @param Tweet
	 *            object to insert into the Tweet table
	 * 
	 * @return Id of the Tweet that is inserted into the Tweet table.
	 */
	@Override
	public Integer insert(Tweet object) {
		return super.insert(INSERT, object);
	}

	/**
	 * Select the latest 100 tweets posted for this user.
	 * 
	 * @param userId
	 *            userId is the follower of the other users.
	 *
	 * @return Collection of models will be returned if there are tweets for
	 *         this user. Else empty Collection object.
	 */
	@Override
	public Collection<Tweet> selectLatestByUsers(Integer userId) {

		final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("userId", userId);

		return super.selectByMappedParameterQuery(SELECT_LATEST_BY_USERS,
				parameterSource);
	}
	
	/**
	 * Select all tweets  from the table
	 * 
	 * @param selectAllQuery
	 *            the query used to select all tweets  from the table
	 * @return the collection of tweet class instances generated from the result
	 *         set
	 */
	public Collection<Tweet> selectAll() {
		final Collection<Tweet> objectList = super.selectAll(SELECT_ALL);
		return objectList;
	}
	
	/**
	 * Delete the tweets created by a user. 
	 * 
	 * @param userId
	 *            userId is the follower of the other users.
	 *
	 * @return 
	 */
	public void deleteByUserId(Integer userId) {

		 super.deleteByUserId(DELETE_BY_USERID,
				userId);
	}

}
