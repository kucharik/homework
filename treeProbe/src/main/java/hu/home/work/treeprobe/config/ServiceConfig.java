package hu.home.work.treeprobe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hu.home.work.treeprobe.service.TreeService;
import hu.home.work.treeprobe.service.TreeServiceHelper;
import hu.home.work.treeprobe.service.TreeServiceHelperImpl;
import hu.home.work.treeprobe.service.TreeServiceImpl;

@Configuration
public class ServiceConfig {


	@Bean
	public TreeService getTreeService()
	{
		return new TreeServiceImpl();
		
	}
	
	@Bean
	public TreeServiceHelper getTreeServiceHelper()
	{
		return new TreeServiceHelperImpl();
		
	}
	
	

}
