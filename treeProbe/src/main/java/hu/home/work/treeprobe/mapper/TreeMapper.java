package hu.home.work.treeprobe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hu.home.work.treeprobe.dao.model.Item;

@Mapper
public interface TreeMapper {

	List<Item> getAllItems(@Param("treeId") Integer treeId, @Param("parentId") Integer parentId);

	Integer move(@Param("newParent") Item newParent, @Param("movable") Item movable);
	
	Integer checkCircle(@Param("new_parent_id") Integer newParentId, @Param("sub_tree_id") Integer subTreeId);

	void insertItem(Item item);
}
