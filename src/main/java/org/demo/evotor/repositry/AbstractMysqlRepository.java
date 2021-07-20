package org.demo.evotor.repositry;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * 
 * @author Andrey Ulyanov
 *
 * @param <TYPE>
 * @param <PK>
 */
public abstract class AbstractMysqlRepository<TYPE, PK> implements IsRepository<TYPE, PK> {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractMysqlRepository.class);

	/* Instance */

	protected NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * 
	 * @param jdbcTemplate
	 */
	public AbstractMysqlRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/* ***** Abstract ***** */

	/**
	 * 
	 * @param domain
	 * @return
	 */
	protected abstract SqlParameterSource getSqlParameterSource(TYPE domain);

	/**
	 * 
	 * @return
	 */
	protected abstract RowMapper<TYPE> getRowMapper();

	/**
	 * 
	 * @return
	 */
	protected abstract String getSqlInsert();

	/**
	 * 
	 * @return
	 */
	protected abstract String getSqlSelect();

	/* ***** Implementation ***** */

	/**
	 * 
	 * @param customer
	 * @return
	 */
	protected List<TYPE> selectBy(String sql, TYPE filter) {
		LOG.debug(">> selectBy(String sql = {}, TYPE filter = {}) ", sql, filter);

		SqlParameterSource params = this.getSqlParameterSource(filter);
		RowMapper<TYPE> mapper = this.getRowMapper();

		LOG.debug("Do request [{}] with params {} and mapper [{}].", sql, params, mapper);
		List<TYPE> result = this.jdbcTemplate.query(sql, params, mapper);

		LOG.debug("<< selectBy(String sql, TYPE filter): {}", result);
		return Collections.unmodifiableList(result);
	}

	/* ***** Implementation ***** */

	@Override
	public int insert(TYPE o) {
		LOG.debug(">> insert(TYPE o = {})", o);

		String sql = this.getSqlInsert();
		SqlParameterSource params = this.getSqlParameterSource(o);

		LOG.debug("Do request [{}] with params [{}].", sql, params);
		int result = this.jdbcTemplate.update(sql, params);

		LOG.debug("<< insert(TYPE o): {}", result);
		return result;
	}

	@Override
	public int update(TYPE o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete(TYPE o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public TYPE read(PK id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<TYPE> select() {
		LOG.debug(">> select()");

		String sql = this.getSqlSelect();
		RowMapper<TYPE> mapper = this.getRowMapper();

		LOG.debug("Do request [{}] with mapper [{}].", sql, mapper);
		List<TYPE> result = this.jdbcTemplate.query(sql, mapper);

		LOG.debug("<< select(): {}", result);
		return Collections.unmodifiableList(result);
	}

}
