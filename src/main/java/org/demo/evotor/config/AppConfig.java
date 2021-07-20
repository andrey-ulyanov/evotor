package org.demo.evotor.config;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "org.demo.evo" })
public class AppConfig {

	/* Class */

	private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

	public static final String DATASOURCE_PLATFORM = "spring.datasource.platform";
	public static final String DATASOURCE_PLATFORM_MYSQL = "mysql";
	public static final String DATASOURCE_PLATFORM_H2 = "h2";
	
	/**
	 * Default
	 */
	public AppConfig() {
		super();
	}

	@PostConstruct
	public void postConstruct() throws SQLException {
		LOG.info("Application start. ");
	}

	@PreDestroy
	public void preDestroy() {
		LOG.info("Application stop. ");
	}

}
