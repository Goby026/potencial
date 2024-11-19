package uml.sc.potencial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:values.properties", encoding = "UTF-8")
public class PotencialApplication {

	public static void main(String[] args) {
		SpringApplication.run(PotencialApplication.class, args);


		System.out.println("Spring App running");
	}

}
