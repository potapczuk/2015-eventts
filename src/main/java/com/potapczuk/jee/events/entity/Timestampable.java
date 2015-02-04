package com.potapczuk.jee.events.entity;

import java.util.Date;

public interface Timestampable {
	
	Date getCreatedAt();
	
	Date getUpdatedAt();
	
	void setCreatedAt(Date createdAt);
	
	void setUpdatedAt(Date updatedAt);
	
}
