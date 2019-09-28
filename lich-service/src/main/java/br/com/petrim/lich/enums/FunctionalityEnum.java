package br.com.petrim.lich.enums;

public enum FunctionalityEnum {

	ADMINISTRATION (1000L, "functionality.admin", 0L),
	USERS (2L, "functionality.users", 1000L),
	GROUPS (3L, "functionality.groups", 1000L);
	
	private Long id;
	private String name;
	private Long parentId;
	
	private FunctionalityEnum(Long id, String name, Long parentId) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
}
