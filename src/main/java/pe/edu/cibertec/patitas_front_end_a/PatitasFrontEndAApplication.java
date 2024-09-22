package pe.edu.cibertec.patitas_front_end_a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PatitasFrontEndAApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatitasFrontEndAApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
