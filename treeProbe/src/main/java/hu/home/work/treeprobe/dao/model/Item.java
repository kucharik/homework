package hu.home.work.treeprobe.dao.model;

import java.util.Arrays;

public class Item {
	
	private Integer id;
	private Integer parentId;
	private String name;
	private Integer treeId;
	private Integer level;
	private Integer version;
	private Integer[] path;
	
	
	
	public Item() {
		super();
	}
	
	public Item(Integer id) {
		super();
		this.id = id;
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public Item setId_(Integer id) {
		this.id = id;
		return this;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public Item setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	public String getName() {
		return name;
	}
	public Item setName(String name) {
		this.name = name;
		return this;
	}
	public Integer getTreeId() {
		return treeId;
	}
	public Item setTreeId(Integer treeId) {
		this.treeId = treeId;
		return this;
	}
	public Integer getLevel() {
		return level;
	}
	public Item setLevel(Integer level) {
		this.level = level;
		return this;
	}
	public Integer getVersion() {
		return version;
	}
	/**
	 * for fluent
	 * @param version
	 * @return
	 */
	public Item setVersion_(Integer version) {
		this.version = version;
		return this;
	}
	/**
	 * for POJO setter
	 * @param version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer[] getPath() {
		return path;
	}

	public void setPath(Integer[] path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", parentId=" + parentId + ", name=" + name + ", treeId=" + treeId + ", level="
				+ level + ", version=" + version + ", path=" + Arrays.toString(path) + "]";
	}
	

	

}
