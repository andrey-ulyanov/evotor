package org.demo.evotor.repository.mysql;

import org.demo.evotor.domain.Customer;
import org.demo.evotor.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryMysql extends AbstractMysqlRepository<Customer, Integer> implements CustomerRepository {

	public CustomerRepositoryMysql(@Autowired NamedParameterJdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Customer readByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SqlParameterSource getSqlParameterSource(Customer domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected RowMapper<Customer> getRowMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSqlSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSqlInsert() {
		// TODO Auto-generated method stub
		return null;
	}

}
