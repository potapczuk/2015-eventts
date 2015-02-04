package com.potapczuk.jee.events.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Flash implements Serializable {
	private static final long serialVersionUID = 1L;

	public void flashMessage(String msg) {
    	FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(msg));
    }
    
    public void flashError(String msg) {
    	FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, ""));
    }
    
    public void flashException(String msg, Exception e) {
    	FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, 
				e.getLocalizedMessage()));
    }
}
