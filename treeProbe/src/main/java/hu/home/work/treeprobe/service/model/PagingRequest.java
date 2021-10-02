package hu.home.work.treeprobe.service.model;

import hu.home.work.treeprobe.dao.model.Item;
import hu.home.work.treeprobe.dao.model.Tree;

public class PagingRequest {

	private int pageSize;
	private int pageNumber;
	private Tree tree;
	private Item fromItem;
	
	public int getPageSize() {
		return pageSize;
	}
	public PagingRequest setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public PagingRequest setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
		return this;
	}
	public Tree getTree() {
		return tree;
	}
	public PagingRequest setTree(Tree tree) {
		this.tree = tree;
		return this;
	}
	public Item getFromItem() {
		return fromItem;
	}
	public void setFromItem(Item fromItem) {
		this.fromItem = fromItem;
	}
	
	
	
	
}
