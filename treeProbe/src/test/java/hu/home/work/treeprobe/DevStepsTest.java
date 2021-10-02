package hu.home.work.treeprobe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.springframework.util.StopWatch;

import hu.home.work.treeprobe.config.CacheConfig;
import hu.home.work.treeprobe.config.DaoConfig;
import hu.home.work.treeprobe.config.ServiceConfig;
import hu.home.work.treeprobe.dao.api.TreeManipulateDao;
import hu.home.work.treeprobe.dao.api.TreeManipulateDaoMngmt;
import hu.home.work.treeprobe.dao.model.Item;
import hu.home.work.treeprobe.dao.model.ItemNode;
import hu.home.work.treeprobe.dao.model.Tree;
import hu.home.work.treeprobe.mapper.TreeMapper;
import hu.home.work.treeprobe.service.TreeService;
import hu.home.work.treeprobe.service.model.PagingRequest;

@Import({DaoConfig.class, CacheConfig.class, ServiceConfig.class})
@MybatisTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class DevStepsTest {

	@Autowired
	private TreeMapper treeMapper;
	
	@Autowired
	private TreeManipulateDao treeDao;
	
	@Autowired
	private TreeManipulateDaoMngmt treeDaoMngmnt;
	
	@Autowired
	private TreeService treeService;
	
	@Test
	@Order(1)
	void treeMapperTest() {
		
		StopWatch timer = new StopWatch();
		timer.start();
			List<Item> aaa = treeMapper.getAllItems(1,null);
		timer.stop();
		System.out.println("Sql hierarchical select, and bean mapping duration is: "+timer.getTotalTimeMillis()+"ms");
		System.out.println("count is: "+aaa.size());
		
	}
	
	@Test
	@Order(2)
	void treeDaoTest() {
		
		StopWatch timer;
		List<Item> aaa = watchDao(false, 1);
		aaa = watchDao(false, 2);

		for (int i = 0; i < 5; i++) {
			aaa=watchDao(true, 1);
		}
		treeDaoMngmnt.evictcache(1);
		aaa = watchDao(true,2);
		aaa = watchDao(false,1);
		aaa = watchDao(true,1);
		System.out.println("count is: "+aaa.size());
		
	}

	private List<Item> watchDao(boolean fromCache, int id) {
		StopWatch timer = new StopWatch();
		timer.start();
			List<Item> aaa = treeDao.getTreeItemsByTreeId(id);
		timer.stop();
		if (fromCache) {
			System.out.println("Cached 'select', and bean mapping duration is treeId["+id+"]: "+timer.getTotalTimeMillis()+"ms");
		} else {
			System.out.println("Sql hierarchical select, and bean mapping duration is treeId["+id+"]: "+timer.getTotalTimeMillis()+"ms");
		}

		return aaa;
	}
	
	@Test
	@Commit
	@Order(3)
	void treeMapperUpdateTest() {
		
		StopWatch timer = new StopWatch();
		timer.start();
			int aaa = treeMapper.move(new Item(2), new Item(11).setVersion_(21));
		timer.stop();
		System.out.println("Moving item between parents duration is: "+timer.getTotalTimeMillis()+"ms");
		System.out.println("updated count is: "+aaa);
		aaa = treeMapper.move(new Item(2), new Item(11).setVersion_(21));
		System.out.println("reupdated count is: "+aaa);
		
	}
	
	@Test
	@Commit
	@Order(4)
	void treeMapperInsertTest() {
		
		StopWatch timer = new StopWatch();
		timer.start();
			Item item = new Item().setName("newItemName").setParentId(11).setTreeId(1);
			treeMapper.insertItem(item);
		timer.stop();
		System.out.println("Insert item duration is: "+timer.getTotalTimeMillis()+"ms");
		System.out.println("inserted item is: "+item);
		
		try {
			item = new Item().setName("newItemName").setParentId(11).setTreeId(-1);
			treeMapper.insertItem(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Commit
	@Order(5)
	void checkCircleTest() {
		
		StopWatch timer = new StopWatch();
		timer.start();
			Integer count = treeMapper.checkCircle(171, 1);
		timer.stop();
		System.out.println("checkCircleTest for subtree duration is: "+timer.getTotalTimeMillis()+"ms");
		System.out.println("checkCircle counter is: "+count);
		
		timer = new StopWatch();
		timer.start();
			count = treeMapper.checkCircle(18087,17);
		timer.stop();
		System.out.println("checkCircleTest for subtree duration is: "+timer.getTotalTimeMillis()+"ms");
		System.out.println("checkCircle counter is: "+count);
	}
	
	
	@Test
	@Commit
	@Order(6)
	void treeServiceTest01() {
		
//			List<Item> items = treeService.pagination(request)

//			treeService.insertItem(item);
		
		StopWatch timer = new StopWatch();
		timer.start();
		    PagingRequest pr = new PagingRequest();
		    pr.setTree(new Tree(1));
		    pr.setFromItem(new Item(2108));
			List<Item> items = treeService.pagination(pr);
		timer.stop();
		System.out.println("Sorting a tree by path at first time( on uncached items) duration is: "+timer.getTotalTimeMillis()+"ms");
		saveTextToFile(items.toString(), "serviceTest");
		
	}
	
	@Test
	@Order(7)
	void treeFromFlattened() {
		List<Item> items = treeMapper.getAllItems(1,2108);
		List<ItemNode> nodes = new ArrayList<ItemNode>();
		items.forEach(item -> {
			nodes.add((ItemNode)item);
		});
		StopWatch timer = new StopWatch();
		timer.start();
//			createTree(nodes,2108);
		timer.stop();
		System.out.println("treeFromFlattened duration is: "+timer.getTotalTimeMillis()+"ms");	
	}
		
		
	
	
	private static void saveTextToFile(String text, String fileName) {
        String filePathAndName = "/" + fileName+ (System.currentTimeMillis()%100000)+".txt";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(".").getFile() + filePathAndName );
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(text);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
}
