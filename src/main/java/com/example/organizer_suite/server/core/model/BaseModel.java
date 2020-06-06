package com.example.organizer_suite.server.core.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

import lombok.Data;

/**
 * Basisklase für alle Model-Objekt.
 * 
 * @author IngoTrautwein
 *
 */
@Data
@Entity
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = -6943586561036345400L;
	
	private @Id @GeneratedValue Long id;
	
	public BaseModel() {
		
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
}
