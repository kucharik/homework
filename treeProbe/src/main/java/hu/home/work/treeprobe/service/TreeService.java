package hu.home.work.treeprobe.service;

import java.util.List;

import hu.home.work.treeprobe.dao.api.exception.StaleStatException;
import hu.home.work.treeprobe.dao.model.Item;
import hu.home.work.treeprobe.dao.model.ItemNode;
import hu.home.work.treeprobe.service.model.PagingRequest;


public interface TreeService {

	//TODO itt lehetne szebbíteni, külön service objektumokkal, beanmappinggel (csini boundary bean mapping) de majd legközelebb ;)
	List<Item> pagination(PagingRequest request);

	List<Item> getTreeItems(int treeId);

	ItemNode getTree(int treeId);

	void moveItems(Item newParent, Item movable) throws StaleStatException;
}
