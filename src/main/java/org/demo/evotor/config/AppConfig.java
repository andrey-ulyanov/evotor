package org.demo.evotor.config;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = { "org.demo.evotor" })
public class AppConfig {

	/* Class */

	private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

	/* Properties */

	public static final String PROPERTIES_ARG = "org.pz.config";

	/* ***** Database ***** */

	public static final String JDBC_DRIVER_HSQLDB = "org.hsqldb.jdbcDriver";
	public static final String JDBC_DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	public static final String JDBC_DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
	public static final String JDBC_CONNECTION_STR_HSQLDB = "jdbc:hsqldb";
	public static final String JDBC_CONNECTION_STR_MYSQL = "jdbc:mysql";
	public static final String JDBC_CONNECTION_STR_ORACLE = "jdbc:oracle";

	/* ***** Routines ***** */
	
	@Autowired
	public NamedParameterJdbcTemplate dataSource;
	

	@PostConstruct
	public void postConstruct() throws SQLException {
		LOG.info("Configuration initialized: {}", this.toString());
		LOG.info("DataSource: {}", dataSource);
	}

	@PreDestroy
	public void preDestroy() {
		LOG.info("Configuration destroy: {}", this.toString());
	}
	
	public String getDriverClassName(String url) {
		String driver = null;

		if (url.contains(AppConfig.JDBC_CONNECTION_STR_MYSQL)) {
			driver = AppConfig.JDBC_DRIVER_MYSQL;
		} else if (url.contains(AppConfig.JDBC_CONNECTION_STR_ORACLE)) {
			driver = AppConfig.JDBC_DRIVER_ORACLE;
		} else if (url.contains(AppConfig.JDBC_CONNECTION_STR_HSQLDB)) {
			driver = AppConfig.JDBC_DRIVER_HSQLDB;
		} else {
			throw new IllegalStateException("Unsupported JDBC driver. ");
		}

		return driver;
	}

}
