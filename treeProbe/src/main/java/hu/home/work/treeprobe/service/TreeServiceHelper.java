package hu.home.work.treeprobe.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import hu.home.work.treeprobe.dao.model.Item;
import hu.home.work.treeprobe.dao.model.ItemNode;


public interface TreeServiceHelper {

	public ItemNode createTreeStructure(List<ItemNode> nodes, Integer rootId);

	@Cacheable("treeSrv")
	public List<Item> getOrderedItemsByTreeId(int treeId, Integer fromItemId);
}
