package hu.home.work.treeprobe.dao.model;

import java.util.ArrayList;
import java.util.Collection;

public class ItemNode extends Item{

	private Collection<ItemNode> childs;

	public Collection<ItemNode> getChilds() {
		if (this.childs == null) {
			this.childs = new ArrayList<ItemNode>();
		}
		return childs;
	}

	public void setChilds(Collection<ItemNode> childs) {
		this.childs = childs;
	}
	
	public void addChild(ItemNode child) {
		getChilds().add(child);
	}

	@Override
	public String toString() {
		return "ItemNode [childs=" + childs + ", toString()=" + super.toString() + "]";
	}
	
	
}
