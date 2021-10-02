package hu.home.work.treeprobe.dao.model;

public class Tree {

	private Integer id;
	private String name;
	private Integer version;
	
	
	public Tree() {
		super();
	}
	
	public Tree(Integer id) {
		super();
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
	
}
