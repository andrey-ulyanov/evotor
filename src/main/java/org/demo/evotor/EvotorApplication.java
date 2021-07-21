package org.demo.evotor;

import org.demo.evotor.config.EvotorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
@SpringBootApplication
@PropertySources({
		@PropertySource(value = EvotorApplication.PROPERTIES_APP_DEFAULT, ignoreResourceNotFound = false, name = "PROPERTIES_APP_DEFAULT"),
		@PropertySource(value = EvotorApplication.PROPERTIES_APP_FILE, ignoreResourceNotFound = true, name = "PROPERTIES_APP_FILE") })
@Import({ EvotorConfig.class })
public class EvotorApplication {

	/**
	 * Name of application argument where a config file path is placed.  
	 */
	public static final String PROPERTIES_APP_ARG = "application.config";

	/**
	 * Default application config takes from classpath.
	 */
	public static final String PROPERTIES_APP_DEFAULT = "classpath:application.yml";

	/**
	 * Production application config takes from secure place in file system.
	 */
	public static final String PROPERTIES_APP_FILE = "file:${" + PROPERTIES_APP_ARG + "}";

	
	/* Class */
	
	public static void main(String[] args) {
		boolean printHelp = false;

		if (args != null) {
			for (String arg : args) {
				switch (arg) {
				case "-h":
				case "--help":
					printHelp = true;
					break;
				default:
					break;
				}
			}
		}

		if (printHelp) {
			System.out.println("Set application properties before start: \n" + "\t" + "Application config: \n"
					+ "\t\t> " + "-Dapplication.config=\"c:\\\\temp\\\\application.yml\"\n" + "\t"
					+ "Logging properties: \n" + "\t\t> " + "-Dlogging.config1=\"c:\\\\temp\\\\logback.xml\"\n" + "");
		} else {
			SpringApplication.run(EvotorApplication.class, args);
		}
	}

}
