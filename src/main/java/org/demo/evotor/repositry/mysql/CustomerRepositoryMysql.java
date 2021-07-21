package org.demo.evotor.repositry.mysql;

import java.util.List;

import org.demo.evotor.config.EvotorConfig;
import org.demo.evotor.domain.Customer;
import org.demo.evotor.repositry.AbstractJdbcRepository;
import org.demo.evotor.repositry.CustomerRepository;
import org.demo.evotor.repositry.utils.Columns;
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
@ConditionalOnProperty(name = EvotorConfig.DATASOURCE_PLATFORM, havingValue = EvotorConfig.DATASOURCE_PLATFORM_MYSQL, matchIfMissing = false)
public class CustomerRepositoryMysql extends AbstractJdbcRepository<Customer, Long> implements CustomerRepository {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerRepositoryMysql.class);

	/* Class */

	public static final String INSERT = "insert into " + Customer.TABLE + " (" + Customer.COLUMN_ID + ", "
			+ Customer.COLUMN_VERSION + ", " + Customer.COLUMN_TIMESTAMP + ", " + Customer.COLUMN_ACTIVE_AFTER + ", "
			+ Customer.COLUMN_ACTIVE_UNTIL + ", " + Customer.COLUMN_LOGIN + ", " + Customer.COLUMN_PASSWORD + ") "
			+ "values" + " ( :" + Columns.getFieldForColumn(Customer.class, Customer.COLUMN_ID) + ", :"
			+ Columns.getFieldForColumn(Customer.class, Customer.COLUMN_VERSION) + ", :"
			+ Columns.getFieldForColumn(Customer.class, Customer.COLUMN_TIMESTAMP) + ", :"
			+ Columns.getFieldForColumn(Customer.class, Customer.COLUMN_ACTIVE_AFTER) + ", :"
			+ Columns.getFieldForColumn(Customer.class, Customer.COLUMN_ACTIVE_UNTIL) + ", :"
			+ Columns.getFieldForColumn(Customer.class, Customer.COLUMN_LOGIN) + ", :"
			+ Columns.getFieldForColumn(Customer.class, Customer.COLUMN_PASSWORD) + " )";

	public static final String SELECT = "select * from " + Customer.TABLE;

	public static final String SELECT_BY_LOGIN = "select * from " + Customer.TABLE + " where " + Customer.COLUMN_LOGIN
			+ " = " + ":" + Columns.getFieldForColumn(Customer.class, Customer.COLUMN_LOGIN);

	/**
	 * 
	 * @param jdbcTemplate
	 */
	public CustomerRepositoryMysql(@Autowired NamedParameterJdbcTemplate jdbcTemplate) {
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
