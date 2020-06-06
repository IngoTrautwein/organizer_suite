package com.example.organizer_suite.server.core.model;

import java.io.Serializable;

/**
 * Basisklase für alle Model-Objekt.
 * 
 * @author IngoTrautwein
 *
 */
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = -6943586561036345400L;
	
	private long id = 0;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
}
