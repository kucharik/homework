package mortoff.line.breaking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mortoff.line.breaking.controller.LineBreakingController;
import mortoff.line.breaking.dao.InMemoryDao;
import mortoff.line.breaking.dao.InMemoryDaoImpl;
import mortoff.line.breaking.service.LineBreakerService;
import mortoff.line.breaking.service.LineBreakerServiceImpl;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public LineBreakerService lineBreakerService() {
		
		return new LineBreakerServiceImpl();
	}
	
//	@Bean
//	public LineBreakingController lineBreakingController() {
//		
//		return new LineBreakingController();
//	}
	
	@Bean
	public InMemoryDao inMemoryDao() {
		
		return new InMemoryDaoImpl();
	}

}
