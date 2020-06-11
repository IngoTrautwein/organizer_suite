package com.example.organizer_suite.server.core.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * Basisklase für alle Model-Objekt.
 * 
 * @author IngoTrautwein
 *
 */
@Data
@MappedSuperclass
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = -6943586561036345400L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public BaseModel() {
		this.id = 0L;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
}
