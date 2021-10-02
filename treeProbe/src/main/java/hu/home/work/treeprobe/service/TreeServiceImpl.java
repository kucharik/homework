package hu.home.work.treeprobe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import hu.home.work.treeprobe.dao.api.TreeManipulateDao;
import hu.home.work.treeprobe.dao.api.exception.StaleStatException;
import hu.home.work.treeprobe.dao.model.Item;
import hu.home.work.treeprobe.dao.model.ItemNode;
import hu.home.work.treeprobe.service.model.PagingRequest;

public class TreeServiceImpl implements TreeService {

	@Autowired
	private TreeManipulateDao treeDao;
	
	@Autowired
	private TreeServiceHelper treeServiceHelper;

	@Override
	public List<Item> pagination(PagingRequest request) {
		//there is a sorted list, afterwards pagination is a simple list traversing with pagenum*offset or list convert to associative structure to direct address for items...
		return treeServiceHelper.getOrderedItemsByTreeId(request.getTree().getId(), request.getFromItem().getId());
	}
	
	@Override
	public List<Item> getTreeItems(int treeId) {
		return treeServiceHelper.getOrderedItemsByTreeId(treeId, null);
	} 
	
	@Override
	public ItemNode getTree(int treeId) {
		
		List<Item> items = treeDao.getTreeItemsByTreeId(treeId);
		List<ItemNode> nodes = new ArrayList<ItemNode>();
		items.forEach(item -> {
			nodes.add((ItemNode)item);
		});
		return treeServiceHelper.createTreeStructure(nodes, null);
	} 
	
	
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void moveItems(Item newParent, Item movable) throws StaleStatException {
		treeDao.move(newParent, movable);
	} 

	
}
