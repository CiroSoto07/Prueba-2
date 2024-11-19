/*
 * WsEfinominaCSJ
 * 8/07/2021
 * ciro.soto@ada.co
 */
package prueba.swagger;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 * Clase de Configuracion de la Documentacion Swagger.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Api docket.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("prueba"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				;
	}
	
	/**
	 * Gets the api info.
	 *
	 * @return the api info
	 */
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Service API",
				"Service API Description",
				"1.0",
				"https://prueba",
				new Contact("Prueba", 
						"https://prueba/", 
						"Ciro Soto"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
				);
		// url swagger: http://localhost:2650/v2/api-docs
	}
}