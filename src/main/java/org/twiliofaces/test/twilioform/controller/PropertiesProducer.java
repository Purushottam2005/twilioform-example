package org.twiliofaces.test.twilioform.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.test.twilioform.annotation.Uid;

@Named
@SessionScoped
public class PropertiesProducer implements Serializable {

	private SelectItem[] siNoItems = null;
	private SelectItem[] smsCallItems = null;
	private static final long serialVersionUID = 1L;

	public PropertiesProducer() {
	}

	@Produces
	@Named
	public SelectItem[] getSiNoItems() {
		if (siNoItems == null) {
			siNoItems = new SelectItem[2];
			siNoItems[0] = new SelectItem(false, "No");
			siNoItems[1] = new SelectItem(true, "Si");
		}
		return siNoItems;
	}

	@Produces
	@Named
	public SelectItem[] getSmsCallItems() {
		if (smsCallItems == null) {
			smsCallItems = new SelectItem[2];
			smsCallItems[0] = new SelectItem(true, "sms");
			smsCallItems[1] = new SelectItem(false, "call");
		}
		return smsCallItems;
	}

	@Inject
	FacesContext facesContext;

	@Produces
	@Uid
	public String getUid() {
		return facesContext.getExternalContext().getRequestParameterMap()
				.get("uid");
	}
}
