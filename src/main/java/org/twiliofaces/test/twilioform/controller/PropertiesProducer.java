package org.twiliofaces.test.twilioform.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
public class PropertiesProducer implements Serializable {

	private SelectItem[] siNoItems = null;
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
}
