package hu.home.work.treeprobe.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import hu.home.work.treeprobe.dao.api.TreeManipulateDao;
import hu.home.work.treeprobe.dao.model.Item;
import hu.home.work.treeprobe.dao.model.ItemNode;

public class TreeServiceHelperImpl implements TreeServiceHelper {

	@Autowired
	private TreeManipulateDao treeDao;
	
	@Override
	public ItemNode createTreeStructure(List<ItemNode> nodes, Integer rootId) {

		Map<Integer, ItemNode> mapTmp = new HashMap<Integer, ItemNode>();

		// Save all nodes to a map
		for (ItemNode current : nodes) {
			mapTmp.put(current.getId(), current);
		}

		// loop and assign parent/child relationships
		for (ItemNode current : nodes) {
			Integer parentId = current.getParentId();

			if (parentId != null) {
				ItemNode parent = mapTmp.get(parentId);
				if (parent != null) {
					current.setParentId(parent.getId());
					parent.addChild(current);
					mapTmp.put(parentId, parent);
					mapTmp.put(current.getId(), current);
				}
			}

		}

		// get the root
		ItemNode root = null;
		for (ItemNode node : mapTmp.values()) {
			if (node.getParentId() == null || node.getParentId() == rootId) {
				root = node;
				break;
			}
		}
		
		return root;

	}
	
	@Override
	public List<Item> getOrderedItemsByTreeId(int treeId, Integer fromItemId) {
		List<Item> items = fromItemId != null ? treeDao.getTreeItemsByTreeId(treeId,fromItemId) : treeDao.getTreeItemsByTreeId(treeId);
		
		Comparator<Item> comp = new Comparator<Item>() {
			
			@Override
			public int compare(Item o1, Item o2) {
				int maxProbes = o1.getPath().length < o2.getPath().length ? o1.getPath().length : o2.getPath().length;
				for (int j = 0; j < maxProbes; j++) {
					if (maxProbes == (j+1)) {
						return o1.getPath()[j].compareTo(o2.getPath()[j]);
					}
					if( o1.getPath()[j] == o2.getPath()[j] ) {
						continue;
					} 
					return o1.getPath()[j].compareTo(o2.getPath()[j]);
				}
				return 0;
			}
		};
		
		items.sort(comp);
		return items;
	}
}
