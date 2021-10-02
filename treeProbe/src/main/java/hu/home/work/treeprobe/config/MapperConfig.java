package hu.home.work.treeprobe.config;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hu.home.work.treeprobe.mapper.TreeMapper;

@Configuration
public class MapperConfig {


	public static final String TREE_PROBE_SQL_SESSION_TEMPLATE_NAME = "treeProbeSqlTemplate";
	
//	@Bean(name="treeMapper")
//	public TreeMapper getTreeMapper(final SqlSessionTemplate sqlSessionTemplate)
//	{
//		org.apache.ibatis.session.Configuration configuration = sqlSessionTemplate.getConfiguration();
//		configuration.addMapper(TreeMapper.class);
//		return sqlSessionTemplate.getMapper(TreeMapper.class);
//		
//	}
	

}
