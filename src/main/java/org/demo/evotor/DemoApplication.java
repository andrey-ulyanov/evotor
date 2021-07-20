package org.demo.evotor;

import org.demo.evotor.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource(value = DemoApplication.PROPERTIES_APP_DEFAULT, ignoreResourceNotFound = false, name = "PROPERTIES_APP_DEFAULT"),
		@PropertySource(value = DemoApplication.PROPERTIES_APP_FILE, ignoreResourceNotFound = true, name = "PROPERTIES_APP_FILE") })
@Import({ AppConfig.class })
public class DemoApplication {

	public static final String PROPERTIES_APP_ARG = "application.config";
	public static final String PROPERTIES_APP_DEFAULT = "classpath:application.yml";
	public static final String PROPERTIES_APP_FILE = "file:${" + PROPERTIES_APP_ARG + "}";

	public static void main(String[] args) {					
		SpringApplication.run(DemoApplication.class, args);
	}

}
