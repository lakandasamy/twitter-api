package com.twitter.dao;

import java.util.Collection;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.twitter.model.UserFollower;

public class UserFollowerDaoImpl extends BaseDao<UserFollower> implements UserFollowerDao {

	private static final String INSERT = "insert into USER_FOLLOWER (UserId, FollowerId, Createddate) values (:userId, :followerId, now())";
	private static final String SELECT_ALL = "select * from USER_FOLLOWER  limit 1000";
	private static final String DELETE_BY_FOLLOWERID = "Delete from USER_FOLLOWER where followerId=:userId";
	private static final String SELECT_BY_FOLLOWER = "select * from USER_FOLLOWER where followerId= :userId limit 1000";
	
	/**
	 * Default Constructor
	 */
	public UserFollowerDaoImpl() {
		super(UserFollower.class);
	}

	/**
	 * Insert the UserFollower object into the DB.
	 * 
	 * @param Tweet
	 *            object to insert into the User_Follower table
	 * 
	 * @return Id of the UserFollower that is inserted into the UserFollower table.
	 */
	@Override
	public Integer insert(UserFollower object) {
		return super.insert(INSERT, object);
	}

	
	/**
	 * Select all Users and the followers for the users from the table
	 * 
	 * @param selectAllQuery
	 *            the query used to select all UserFollower  from the table
	 * @return the collection of UserFollower class instances generated from the result
	 *         set
	 */
	public Collection<UserFollower> selectAll() {
		final Collection<UserFollower> objectList = super.selectAll(SELECT_ALL);
		return objectList;
	}

	/**
	 * Delete the User follower record from the User_Follower table 
	 * 
	 * @param userId
	 *            userId is the follower of the other users.
	 *
	 * @return 
	 */
	@Override
	public Integer deleteByFollowerId(Integer userId) {

		final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("userId", userId);

		return super.deleteByMappedParameterQuery(DELETE_BY_FOLLOWERID,
				parameterSource);
	}

	/**
	 * Select all Users and the followers for the followers from the table
	 * 
	 * @param userId
	 *             FollowerId to find the users and followers records.
	 * @return the collection of UserFollower class instances generated from the result
	 *         set
	 */
	public Collection<UserFollower> selectByFollowerId(final Integer userId) {
		final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("userId", userId);
		
		final Collection<UserFollower> objectList = super.selectByMappedParameterQuery(SELECT_BY_FOLLOWER,
				parameterSource);
		return objectList;
	}
}
