package br.com.petrim.lich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "access_functionality")
public class Functionality extends AbstractEntity {

	@Id
	@Column(name = "id", length = 20, nullable = false)
	private Long id;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "parent_id", length = 20, nullable = false)
	private Long parentId;
	
	{
		this.noAudit();
	}
	
	public Functionality() {
		// TODO Auto-generated constructor stub
	}
	
	public Functionality(Long id, String name, Long parentId) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}
	
	@Override
	public Object getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
