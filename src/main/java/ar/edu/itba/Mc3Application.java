package ar.edu.itba;

import ar.edu.itba.domain.WSClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Mc3Application {

	public static void main(String[] args) {
		SpringApplication.run(Mc3Application.class, args);
		WSClient wsClient = new WSClient();
		wsClient.startConsuming();
	}
}
