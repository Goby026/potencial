package uml.sc.potencial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:values.properties", encoding = "UTF-8")
public class PotencialApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${server.port}")
	String APP_PORT;

	public static void main(String[] args) {
		SpringApplication.run(PotencialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Spring App running on port: {}", this.APP_PORT);
	}
}
