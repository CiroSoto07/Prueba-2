/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The Class PruebaApplication.
 */
@ComponentScan
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class PruebaApplication extends SpringBootServletInitializer  {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

    	/**
	     * Configure.
	     *
	     * @param application the application
	     * @return the spring application builder
	     */
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder  application) {
	        return application.sources(PruebaApplication.class);
	    }
}
