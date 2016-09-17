package com.twitter.dao;

import java.util.Collection;

import com.twitter.model.BaseObject;

/**
 * Dao interface to hold the common methods that should be implemented in the base class.
 */
public interface Dao <T extends BaseObject>{
	
	/**
	 * Select all model objects from the table
	 * 
	 * @param selectAllQuery
	 *            the query used to select all model objects from the table
	 * @return the collection of model class instances generated from the result
	 *         set
	 */
    Collection<T> selectAll(final String selectAllQuery);

    /**
     * Delete model object from table by the UserId. This needs to be implemented.
     * 
     * @param deleteByIdQuery
     *            the query used to delete a single model object from the table
     *            by id
     * @param id
     *            the id of the model object
     */
     void deleteByUserId(String deleteQuery, Integer userId);

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
    Integer insert(final T object);
}
