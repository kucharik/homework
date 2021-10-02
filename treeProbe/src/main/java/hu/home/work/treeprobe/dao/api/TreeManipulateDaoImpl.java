package hu.home.work.treeprobe.dao.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;

import hu.home.work.treeprobe.dao.api.exception.StaleStatException;
import hu.home.work.treeprobe.dao.api.exception.TreeProbeErrorCodes;
import hu.home.work.treeprobe.dao.model.Item;
import hu.home.work.treeprobe.mapper.TreeMapper;

public class TreeManipulateDaoImpl implements TreeManipulateDao {

	@Autowired
	private TreeMapper treeMapper;
	
	@Cacheable("treeDao")
	@Override
	public List<Item> getTreeItemsByTreeId(int id) {
		return getTreeItemsByTreeId(id,null);
	}
	
	@Cacheable("subTree") //nyilván majd ehhez is kell a cache adminisztráció, az most későbbre tolódik...
	@Override
	public List<Item> getTreeItemsByTreeId(int treeId, Integer subTreeId) {
		return treeMapper.getAllItems(treeId, subTreeId);
	}
	

	@CacheEvict(value = "treeDao", key = "#Item.treeId") //ezt innen elrakni, mert transaction rollback esetén is kiváltja az evictet....vagy mégsem :D https://github.com/spring-projects/spring-framework/issues/23192
	@Override
	public void move(Item newParent, Item movable) throws StaleStatException {
		
		Assert.notNull(movable, "TreeManipulateDao.move Movable Item can not be null!");
		Assert.notNull(newParent, "TreeManipulateDao.move NewParent Item can not be null!");
		
		checkCircle(newParent.getId(), movable.getId());
		
		int result = treeMapper.move(newParent, movable);
		if (result != 1) {
			throw new StaleStatException("Item state overdued itemId:["+movable.getId()+"]", TreeProbeErrorCodes.ITEM_ERROR_CODE_001);
		}
	}

	@Override
	public void insertItem(Item item) throws StaleStatException {
		try {
			treeMapper.insertItem(item);
		} catch (Exception e) {
			throw new StaleStatException("Error on insert item: "+item, TreeProbeErrorCodes.ITEM_ERROR_CODE_002);
		}
	}
	
	
	/**
	 * Detects if candidates would become graph circle founders... :)
	 * @param newParentId
	 * @param subTreeId
	 * @throws StaleStatException
	 */
	private void checkCircle(int newParentId, int subTreeId) throws StaleStatException {
		if (treeMapper.checkCircle(newParentId, subTreeId) > 0) 
			throw new StaleStatException("Potentionally circle newParentId:["+newParentId+"] subTreeId:["+subTreeId+"]", TreeProbeErrorCodes.ITEM_ERROR_CODE_003);
	}



}
