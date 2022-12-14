package mortoff.line.breaking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;

import mortoff.line.breaking.configuration.BeanConfiguration;
import mortoff.line.breaking.configuration.ServerConfiguration;

@SpringBootApplication
@EnableAsync
@EnableWebFlux
@Import(value = {//
		ServerConfiguration.class,//
		BeanConfiguration.class//
	})
public class LineBreakingApp {
	
	private static Logger logger = LoggerFactory.getLogger(LineBreakingApp.class);

	public static void main(String[] args) {
		SpringApplication.run(LineBreakingApp.class, args);
	}
	

}
