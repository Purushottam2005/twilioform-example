package org.twiliofaces.test.twilioform.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.twiliofaces.test.twilioform.model.SmsToSend;

@ApplicationScoped
@Named
public class SmsRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, SmsToSend> sms;

	public void save(SmsToSend smsToSend) {
		getSms().put(smsToSend.getUid(), smsToSend);
	}

	public boolean exist(String uid) {
		return getSms().containsKey(uid);
	}

	public SmsToSend find(String uid) {
		if (exist(uid))
			return getSms().get(uid);
		return null;
	}

	public Map<String, SmsToSend> getSms() {
		if (this.sms == null)
			this.sms = new ConcurrentHashMap<String, SmsToSend>();
		return sms;
	}

	public void setSms(Map<String, SmsToSend> sms) {
		this.sms = sms;
	}

	@Produces
	@Named
	public List<SmsToSend> getAllSms() {
		if (getSms() != null)
			return new ArrayList(getSms().values());
		return new ArrayList<SmsToSend>();
	}

}
