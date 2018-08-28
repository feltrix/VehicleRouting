package br.com.ifood.vehiclerouting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class VehicleRoutingApplication {


	public static void main(String[] args) {
		SpringApplication.run(VehicleRoutingApplication.class, args);
	}
}
