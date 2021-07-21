package org.demo.evotor.repositry.h2;

import java.util.List;

import org.demo.evotor.config.EvotorConfig;
import org.demo.evotor.domain.Currency;
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
@ConditionalOnProperty(name = EvotorConfig.DATASOURCE_PLATFORM, havingValue = EvotorConfig.DATASOURCE_PLATFORM_H2, matchIfMissing = false)
public class CustomerAccountRepositroyH2 extends AbstractJdbcRepository<CustomerAccount, Long>
		implements CustomerAccountRepository {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountRepositroyH2.class);

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
	public CustomerAccountRepositroyH2(@Autowired NamedParameterJdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	/* ***** Implementation ***** */

	@Override
	protected SqlParameterSource getSqlParameterSource(CustomerAccount domain) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(domain) {
			@Override
			public Object getValue(String paramName) throws IllegalArgumentException {
				Object value = super.getValue(paramName);
				if (value instanceof Currency) {
					return value.toString();
				}
				return value;
			}
		};
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
