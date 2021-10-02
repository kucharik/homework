package hu.home.work.treeprobe.dao.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;

public class TreeManipulateDaoMngmtImpl implements TreeManipulateDaoMngmt {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TreeManipulateDaoMngmtImpl.class);

	/**
	 * removes tree from trees cache by the given tree_id
	 */
	// @CacheEvict(value="tree", allEntries=true) not so sophisticated :)
	@CacheEvict(value = "treeDao", key = "#id")
	@Override
	public void evictcache(int id) {
		LOGGER.debug("Tree with id:["+id+"] evicted, this point where another cacheimplementation like EHCACHE fires an event into JMS,MQ,KAFKA.... for cache synchronization on nodes");

	}

}
