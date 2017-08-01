package challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class ChallengeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}
}
