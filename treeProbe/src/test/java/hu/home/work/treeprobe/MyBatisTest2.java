package hu.home.work.treeprobe;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;

import hu.home.work.treeprobe.config.CacheConfig;
import hu.home.work.treeprobe.config.DaoConfig;
import hu.home.work.treeprobe.dao.api.TreeManipulateDao;
import hu.home.work.treeprobe.dao.api.exception.StaleStatException;
import hu.home.work.treeprobe.dao.api.exception.TreeProbeErrorCodes;
import hu.home.work.treeprobe.dao.model.Item;
import hu.home.work.treeprobe.mapper.TreeMapper;

@Import({DaoConfig.class, CacheConfig.class})
@MybatisTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MyBatisTest2 {

	@Autowired
	private TreeMapper treeMapper;
	
	@Autowired
	private TreeManipulateDao treeDao;
	
	
	@Test
	void treeDaoUpdateTest() {
		
			
			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				treeDao.move(null, new Item(11).setVersion_(21));
		    });

		    String expectedText = "NewParent";
		    String actualMessage = exception.getMessage();

		    assertTrue(actualMessage.contains(expectedText));
		    
		    exception = assertThrows(IllegalArgumentException.class, () -> {
				treeDao.move(new Item(11), null);
		    });

		    expectedText = "Movable";
		    actualMessage = exception.getMessage();

		    assertTrue(actualMessage.contains(expectedText));
			
		    
		    exception = assertThrows(StaleStatException.class, () -> {
				treeDao.move(new Item(2), new Item(11).setVersion_(-1));
		    });

		    expectedText = TreeProbeErrorCodes.ITEM_ERROR_CODE_001;
		    actualMessage = ((StaleStatException)exception).getCode();

		    assertTrue(actualMessage.equalsIgnoreCase(expectedText));
		
	}
	
	
	
}
