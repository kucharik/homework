package mortoff.line.breaking.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@Configuration
public class ServerConfiguration {
	
	Logger log = LoggerFactory.getLogger(ServerConfiguration.class);
	
	@Bean
	public WebFilter webFilter() {
		log.trace("INIT webFilter!!!!!!!!!!!!!!!!");
		return new LineBreakerWebFilter();
	}
	
	

}
