package hu.home.work.treeprobe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hu.home.work.treeprobe.dao.api.TreeManipulateDao;
import hu.home.work.treeprobe.dao.api.TreeManipulateDaoImpl;
import hu.home.work.treeprobe.dao.api.TreeManipulateDaoMngmt;
import hu.home.work.treeprobe.dao.api.TreeManipulateDaoMngmtImpl;

@Configuration
public class DaoConfig {


	@Bean
	public TreeManipulateDao getTreeManipulateDao()
	{
		return new TreeManipulateDaoImpl();
		
	}
	
	@Bean
	public TreeManipulateDaoMngmt getTreeManipulateDaoMngmt()
	{
		return new TreeManipulateDaoMngmtImpl();
		
	}
	

}
