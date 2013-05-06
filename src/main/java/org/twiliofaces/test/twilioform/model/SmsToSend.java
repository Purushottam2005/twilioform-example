package org.twiliofaces.test.twilioform.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class SmsToSend implements Serializable {

	private static final long serialVersionUID = 1L;
	private String uid;
	private String to;
	private String text;
	private Date when;

	public SmsToSend() {
		UUID uid = UUID.randomUUID();
		this.uid = uid.toString();
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
