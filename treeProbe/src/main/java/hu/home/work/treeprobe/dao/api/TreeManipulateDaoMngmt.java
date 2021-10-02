package hu.home.work.treeprobe.dao.api;

import java.util.List;

import hu.home.work.treeprobe.dao.api.exception.StaleStatException;
import hu.home.work.treeprobe.dao.model.Item;

public interface TreeManipulateDaoMngmt {
	
	public void evictcache(int id);
	

}
