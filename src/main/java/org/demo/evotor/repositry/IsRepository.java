package org.demo.evotor.repositry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.demo.evotor.domain.IsDomain;

/***
 * Marker for database repository.
 * 
 * @author Andrey Ulyanov
 *
 * @param <TYPE> Database entity type.
 * @param <PK>   Primary key type.
 */
public interface IsRepository<TYPE extends IsDomain, PK> {

	/**
	 * Insert row.
	 * 
	 * @param o
	 * @return
	 */
	int insert(TYPE o);

	
	/**
	 * Update row.
	 * 
	 * @param o
	 * @return
	 * @throws DataAccessException
	 */
	int update(TYPE o);


	/**
	 * Delete by instance.
	 * 
	 * @param o
	 * @return
	 * @throws DataAccessException
	 */
	int delete(TYPE o);


	/**
	 * Read row by ID.
	 * 
	 * @param id
	 * @return
	 */
	TYPE read(PK id);

	/**
	 * Read row by ID.
	 * 
	 * @param ids
	 * @return
	 */
	default List<TYPE> readList(Collection<? extends PK> ids) {
		ArrayList<TYPE> result = new ArrayList<TYPE>(ids.size());
		for (PK id : ids) {
			TYPE o = this.read(id);
			if (o != null) {
				result.add(o);
			}
		}
		return result;
	}

	/**
	 * Count of rows in table.
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	Long count();

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	List<TYPE> select();


	/* ***** Defaults ***** */

	/**
	 * Read only one element from result set, else throw exception.
	 * 
	 * @param list
	 * @return
	 */
	default <X> X once(Collection<? extends X> list) {
		if (list == null) {
			throw new RuntimeException("Result is is NULL. ");
		} else if (list.size() > 1) {
			throw new RuntimeException("Result contains more then one item. ");
		} else if (list.size() == 0) {
			return null;
		} else {
			return list.iterator().next();
		}
	}

}
