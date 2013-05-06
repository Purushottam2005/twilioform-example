package org.twiliofaces.test.twilioform.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class CallToMake implements Serializable {

	private static final long serialVersionUID = 1L;
	private String uid;
	private String to;
	private String url;
	private String text;
	private Date when;

	private String accountSid;
	private String twilioToken;
	private String from;

	public CallToMake() {
		UUID uid = UUID.randomUUID();
		this.uid = uid.toString();
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "CallToMake [uid=" + uid + ", to=" + to + ", url=" + url
				+ ", text=" + text + ", when=" + when + "]";
	}

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getTwilioToken() {
		return twilioToken;
	}

	public void setTwilioToken(String twilioToken) {
		this.twilioToken = twilioToken;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
