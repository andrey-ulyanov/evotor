package org.demo.evotor.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.demo.evotor.domain.Currency;
import org.demo.evotor.domain.CustomerAccount;
import org.demo.evotor.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositroyMysql extends AbstractMysqlRepository<CustomerAccount, Integer>
		implements AccountRepository, SqlParameterSource {

	/**
	 * 
	 * @param jdbcTemplate
	 */
	public AccountRepositroyMysql(@Autowired NamedParameterJdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public int insert(CustomerAccount o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(CustomerAccount o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(CustomerAccount o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CustomerAccount read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String paramName) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasValue(String paramName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected SqlParameterSource getSqlParameterSource(CustomerAccount domain) {
		return new BeanPropertySqlParameterSource(domain);
	}

	@Override
	protected RowMapper<CustomerAccount> getRowMapper() {
		return new BeanPropertyRowMapper<CustomerAccount>(CustomerAccount.class);
	}

	@Override
	protected String getSqlSelect() {
		return "select * from " + CustomerAccount.TABLE;
	}

	@Override
	protected String getSqlInsert() {
		return "insert into " + CustomerAccount.TABLE + " (" + CustomerAccount.COLUMN_ID + ", "
				+ CustomerAccount.COLUMN_VERSION + ", " + CustomerAccount.COLUMN_TIMESTAMP + ", "
				+ CustomerAccount.COLUMN_CURRENCY + ", " + CustomerAccount.COLUMN_BALANCE + ") values (" + ":"
				+ CustomerAccount.COLUMN_ID + ", " + ":" + CustomerAccount.COLUMN_VERSION + ", " + ":"
				+ CustomerAccount.COLUMN_TIMESTAMP + ", " + ":" + CustomerAccount.COLUMN_CURRENCY + ", " + ":"
				+ CustomerAccount.COLUMN_BALANCE + ")";
	}

}
