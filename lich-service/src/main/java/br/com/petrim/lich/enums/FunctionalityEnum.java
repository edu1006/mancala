package br.com.petrim.lich.enums;

public enum FunctionalityEnum {

	/**
	 * Administration Functionalities
	 */
	ADMINISTRATION (1000L, "functionality.admin", 0L),
	USERS (2L, "functionality.users", 1000L),
	GROUPS (3L, "functionality.groups", 1000L),
	AUTHENTICATION (4L, "functionality.authentication", 1000L),
	CLEAN_LOG (16L, "functionality.cleanlog", 1000L),
	HOLIDAY (17L, "functionality.holiday", 1000L),
	
	/**
	 * Processes Functionalities
	 */
	PROCESSES (2000L, "functionality.processes", 0L),
	PARAMETER (14L, "functionality.parameter", 2000L),
	AGENT (7L, "functionality.agent", 2000L),
	PROCESS (5L, "functionality.process", 2000L),
	EXECUTOR (6L, "functionality.executor", 2000L),
	
	/**
	 * Reports Functionalities
	 */
	
	;
	
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
