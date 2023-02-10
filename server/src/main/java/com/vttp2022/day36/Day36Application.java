package com.vttp2022.day36;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day36Application {

	public static void main(String[] args) {
		SpringApplication.run(Day36Application.class, args);
	}

	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// return new WebMvcConfigurer() {
	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// registry.addMapping("/api/game").allowedOrigins("*");
	// }
	// };
	// }
}
