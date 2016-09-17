package com.twitter.dao;

import java.util.Collection;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.twitter.model.BaseObject;

/**
 * The Class BaseDao. This class describes the base behavior of a DAO (CRUD).
 * Utilizes the named parameter support provided by Spring 4.x in addition to
 * the object mapping facilities of Spring 4.x
 * 
 */
public abstract class BaseDao<T extends BaseObject> implements Dao<T> {

	private Class<T> clazz;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * Constructor
	 */
	public BaseDao(final Class<T> clazz) {
		this.clazz = clazz;

	}

	/**
	 * Select all model objects from the table
	 * 
	 * @param selectAllQuery
	 *            the query used to select all model objects from the table
	 * @return the collection of model class instances generated from the result
	 *         set
	 */
	public Collection<T> selectAll(final String selectAllQuery) {
		final Collection<T> objectList = namedParameterJdbcTemplate.query(
				selectAllQuery, new MapSqlParameterSource(),
				BeanPropertyRowMapper.newInstance(clazz));
		return objectList;
	}

	/**
	 * Delete model object from table by primary key (id). This needs to be
	 * implemented.
	 * 
	 * @param deleteByIdQuery
	 *            the query used to delete a single model object from the table
	 *            by id
	 * @param id
	 *            the id of the model object
	 */
	@Override
	public void deleteByUserId(String deleteQuery, Integer userId) {
		final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("userId", userId);
		
		deleteByMappedParameterQuery(deleteQuery,
				parameterSource);
	}

	/**
	 * Insert a new model object into the table
	 * 
	 * @param insertQuery
	 *            the query used to insert a single model object into the table
	 * @param object
	 *            the model class instance from which to obtain the parameter
	 *            values
	 * @return the new id of the inserted model object
	 */

	public Integer insert(final String insertQuery, final T object) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(insertQuery,
				new BeanPropertySqlParameterSource(object), keyHolder);
		if (keyHolder.getKey() == null) {
			return null;
		}
		return keyHolder.getKey().intValue();
	}

	/**
	 * Delete model object from table by Query
	 * 
	 * @param deleteByQuery
	 *            the query used to delete a single model object from the table
	 * 
	 * @param parameterMap
	 *            the parameterMap contains the constraints.
	 */
	public int deleteByMappedParameterQuery(final String deleteByQuery,
			final MapSqlParameterSource parameterSource) {
		return namedParameterJdbcTemplate
				.update(deleteByQuery, parameterSource);
	}

	/**
	 * Run the select query and mapped the result to the collection of Objects.
	 * 
	 * @param selectQuery
	 *            the query to select the model objects from the table.
	 * @param parameterSource
	 *            the mapped parameters for the query. values
	 * @return Collection of models will be returned of there are records. Else
	 *         0 size Collection object.
	 */

	public Collection<T> selectByMappedParameterQuery(String selectQuery,
			MapSqlParameterSource parameterSource) {
		return namedParameterJdbcTemplate.query(selectQuery, parameterSource,
				BeanPropertyRowMapper.newInstance(clazz));
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate parameterJdbcTemplate) {
		namedParameterJdbcTemplate = parameterJdbcTemplate;
	}

}
