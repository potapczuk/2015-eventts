package com.potapczuk.jee.events.util;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;

public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	public String getParam(String key) {
    	Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
		
    	return params.get(key);
    }
}
