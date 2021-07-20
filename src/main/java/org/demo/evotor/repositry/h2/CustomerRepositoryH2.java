package org.demo.evotor.repositry.h2;

import java.util.List;

import org.demo.evotor.config.EvotorConfig;
import org.demo.evotor.domain.Customer;
import org.demo.evotor.repositry.AbstractJdbcRepository;
import org.demo.evotor.repositry.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
@Repository
@ConditionalOnProperty(name = EvotorConfig.DATASOURCE_PLATFORM, havingValue = EvotorConfig.DATASOURCE_PLATFORM_H2, matchIfMissing = false)
public class CustomerRepositoryH2 extends AbstractJdbcRepository<Customer, Long> implements CustomerRepository {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerRepositoryH2.class);

	/* Class */

	public static final String INSERT = "insert into " + Customer.TABLE + " (" + Customer.COLUMN_ID + ", "
			+ Customer.COLUMN_VERSION + ", " + Customer.COLUMN_TIMESTAMP + ", " + Customer.COLUMN_ACTIVE_AFTER + ", "
			+ Customer.COLUMN_ACTIVE_UNTIL + ", " + Customer.COLUMN_LOGIN + ", " + Customer.COLUMN_PASSWORD + ") "
			+ "values" + " ( :id, :version, :timestamp, :activeAfter, :activeUntil, :login, :password )";

	public static final String SELECT = "select * from " + Customer.TABLE;
	
	public static final String SELECT_BY_LOGIN = "select * from " + Customer.TABLE + " where " + Customer.COLUMN_LOGIN
			+ " = " + ":login";

	/**
	 * 
	 * @param jdbcTemplate
	 */
	public CustomerRepositoryH2(@Autowired NamedParameterJdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}
	
	/* ***** Implementation ***** */

	@Override
	public Customer readByLogin(String login) {
		Customer filter = new Customer().setLogin(login);
		List<Customer> result = this.jdbcTemplate.query(SELECT_BY_LOGIN, this.getSqlParameterSource(filter),
				this.getRowMapper());
		return once(result);
	}

	@Override
	protected SqlParameterSource getSqlParameterSource(Customer domain) {		
		return new BeanPropertySqlParameterSource(domain);
	}

	@Override
	protected RowMapper<Customer> getRowMapper() {
		return new BeanPropertyRowMapper<Customer>(Customer.class);
	}

	@Override
	protected String getSqlSelect() {
		return SELECT;
	}

	@Override
	protected String getSqlInsert() {
		return INSERT;
	}

}
