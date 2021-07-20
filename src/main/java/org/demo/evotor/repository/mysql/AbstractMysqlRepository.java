package org.demo.evotor.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.demo.evotor.domain.CustomerAccount;
import org.demo.evotor.repository.IsRepository;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

	/* Instance */

	protected NamedParameterJdbcTemplate jdbcTemplate;
	protected BiConsumer<TYPE, Map<String, Object>> mapper;
	protected BiConsumer<Map<String, Object>, TYPE> unmapper;
	protected Supplier<TYPE> constructor;

	/**
	 * 
	 * @param jdbcTemplate
	 */
	public AbstractMysqlRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* ***** Abstract ***** */
	
	
	
	protected abstract SqlParameterSource getSqlParameterSource(TYPE domain);
	
	protected abstract RowMapper<TYPE> getRowMapper();
	
	protected abstract String getSqlSelect();
	protected abstract String getSqlInsert();

	
	
	@Override
	public int insert(TYPE o) {
		int result = this.jdbcTemplate.update(this.getSqlInsert(), this.getSqlParameterSource(o));
		return result;
	}

	@Override
	public int update(TYPE o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(TYPE o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TYPE read(PK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TYPE> select() {
		List<TYPE> result = this.jdbcTemplate.query(this.getSqlSelect(), this.getRowMapper());
		return Collections.unmodifiableList(result);
	}

}
