package org.demo.evotor;

import org.demo.evotor.config.EvotorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource(value = EvotorApplication.PROPERTIES_APP_DEFAULT, ignoreResourceNotFound = false, name = "PROPERTIES_APP_DEFAULT"),
		@PropertySource(value = EvotorApplication.PROPERTIES_APP_FILE, ignoreResourceNotFound = true, name = "PROPERTIES_APP_FILE") })
@Import({ EvotorConfig.class })
public class EvotorApplication {

	public static final String PROPERTIES_APP_ARG = "application.config";
	public static final String PROPERTIES_APP_DEFAULT = "classpath:application.yml";
	public static final String PROPERTIES_APP_FILE = "file:${" + PROPERTIES_APP_ARG + "}";

	public static void main(String[] args) {					
		SpringApplication.run(EvotorApplication.class, args);
	}

}
