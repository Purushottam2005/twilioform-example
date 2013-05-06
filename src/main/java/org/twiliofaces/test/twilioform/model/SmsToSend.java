package org.twiliofaces.test.twilioform.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import org.twiliofaces.annotations.configuration.TwilioNumber;
import org.twiliofaces.annotations.configuration.TwilioToken;

public class SmsToSend implements Serializable {

	private static final long serialVersionUID = 1L;
	private String uid;
	private String to;
	private String text;
	private Date when;

	private String accountSid;
	private String twilioToken;
	private String from;

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

	@Override
	public String toString() {
		return "SmsToSend [uid=" + uid + ", to=" + to + ", text=" + text
				+ ", when=" + when + "]";
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
