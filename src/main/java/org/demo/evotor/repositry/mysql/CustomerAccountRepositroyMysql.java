package org.demo.evotor.repositry.mysql;

import java.sql.Types;
import java.util.List;

import org.demo.evotor.config.EvotorConfig;
import org.demo.evotor.domain.Customer;
import org.demo.evotor.domain.CustomerAccount;
import org.demo.evotor.repositry.AbstractJdbcRepository;
import org.demo.evotor.repositry.CustomerAccountRepository;
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
public class CustomerAccountRepositroyMysql extends AbstractJdbcRepository<CustomerAccount, Long>
		implements CustomerAccountRepository {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountRepositroyMysql.class);

	/* Class */

	public static final String INSERT = "insert into " + CustomerAccount.TABLE + " (" + CustomerAccount.COLUMN_ID + ", "
			+ CustomerAccount.COLUMN_VERSION + ", " + CustomerAccount.COLUMN_TIMESTAMP + ", "
			+ CustomerAccount.COLUMN_CURRENCY + ", " + CustomerAccount.COLUMN_BALANCE + ", "
			+ CustomerAccount.COLUMN_ID_CUSTOMER + ") " + "values" + " ( :"
			+ Columns.getFieldForColumn(CustomerAccount.class, CustomerAccount.COLUMN_ID) + ", :"
			+ Columns.getFieldForColumn(CustomerAccount.class, CustomerAccount.COLUMN_VERSION) + ", :"
			+ Columns.getFieldForColumn(CustomerAccount.class, CustomerAccount.COLUMN_TIMESTAMP) + ", :"
			+ Columns.getFieldForColumn(CustomerAccount.class, CustomerAccount.COLUMN_CURRENCY) + ", :"
			+ Columns.getFieldForColumn(CustomerAccount.class, CustomerAccount.COLUMN_BALANCE) + ", :"
			+ Columns.getFieldForColumn(CustomerAccount.class, CustomerAccount.COLUMN_ID_CUSTOMER) + " )";

	public static final String SELECT = "select * from " + CustomerAccount.TABLE;

	public static final String SELECT_BY_ID_CUSTOMER = "select * from " + CustomerAccount.TABLE + " where "
			+ CustomerAccount.COLUMN_ID_CUSTOMER + " = " + ":"
			+ Columns.getFieldForColumn(CustomerAccount.class, CustomerAccount.COLUMN_ID_CUSTOMER);

	/**
	 * 
	 * @param jdbcTemplate
	 */
	public CustomerAccountRepositroyMysql(@Autowired NamedParameterJdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	/* ***** Implementation ***** */

	@Override
	protected SqlParameterSource getSqlParameterSource(CustomerAccount domain) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(domain);
		params.registerSqlType("currency", Types.CHAR);
		return params;
	}

	@Override
	protected RowMapper<CustomerAccount> getRowMapper() {
		return new BeanPropertyRowMapper<CustomerAccount>(CustomerAccount.class);
	}

	@Override
	protected String getSqlSelect() {
		return SELECT;
	}

	@Override
	protected String getSqlInsert() {
		return INSERT;
	}

	/* ***** Implementation ***** */

	@Override
	public List<CustomerAccount> selectBy(Customer customer) {
		LOG.debug(">> selectBy(Customer customer = {})", customer);

		CustomerAccount filter = new CustomerAccount().setIdCustomer(customer.getId());
		List<CustomerAccount> result = super.selectBy(SELECT_BY_ID_CUSTOMER, filter);

		LOG.debug("<< sselectBy(Customer customer = {}): {}", result);
		return result;
	}

}
