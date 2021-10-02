package hu.home.work.treeprobe.dao.api;

import java.util.List;

import hu.home.work.treeprobe.dao.api.exception.StaleStatException;
import hu.home.work.treeprobe.dao.model.Item;

public interface TreeManipulateDao {
	
	/**
	 * Returns all items from the given tree in flattened format
	 * @param id
	 * @return
	 */
	public List<Item> getTreeItemsByTreeId(int id);
	
	public List<Item> getTreeItemsByTreeId(int treeId, Integer subTreeId);
	
	public void move(Item newParent, Item movable) throws StaleStatException;
	
	public void insertItem(Item item) throws StaleStatException;

}
