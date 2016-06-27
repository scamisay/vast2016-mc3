package ar.edu.itba;

import ar.edu.itba.domain.WSClient;
import ar.edu.itba.domain.impl.FilePersistentEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Mc3Application {

	public static void main(String[] args) {
		SpringApplication.run(Mc3Application.class, args);
/*		WSClient wsClient = new WSClient(new FilePersistentEntity("webSocketDump.data"));
		wsClient.startConsuming();*/
	}
}
