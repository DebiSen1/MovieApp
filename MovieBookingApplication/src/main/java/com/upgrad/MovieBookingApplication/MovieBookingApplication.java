package com.upgrad.MovieBookingApplication;

import io.swagger.models.Model;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.Map;

@SpringBootApplication
public class MovieBookingApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(MovieBookingApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
