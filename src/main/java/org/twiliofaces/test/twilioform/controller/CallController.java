package org.twiliofaces.test.twilioform.controller;

import java.io.Serializable;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.annotations.TwilioScope;
import org.twiliofaces.annotations.notification.CallSid;
import org.twiliofaces.annotations.notification.From;
import org.twiliofaces.extension.TwilioScoped;
import org.twiliofaces.test.twilioform.annotation.Uid;
import org.twiliofaces.test.twilioform.model.CallToMake;
import org.twiliofaces.test.twilioform.repository.CallRepository;

@TwilioScope
@Named
public class CallController implements TwilioScoped, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CallRepository callRepository;

	@Inject
	@CallSid
	Instance<String> callSid;

	@Inject
	@From
	Instance<String> from;

	private CallToMake callToMake;

	@Inject
	@Uid
	Instance<String> uid;

	int count = 0;

	public CallController() {
	}

	public String getCallSid() {
		return callSid.get();
	}

	public String getFrom() {
		return from.get();
	}

	public void log() {
		count++;
		System.out.println("CALL SID: " + getCallSid() + " count: " + count);
		CallToMake callToMake = callRepository.find(getUid());
		setCallToMake(callToMake);
	}

	public CallToMake getCallToMake() {
		return callToMake;
	}

	public void setCallToMake(CallToMake callToMake) {
		this.callToMake = callToMake;
	}

	public String getUid() {
		return uid.get();
	}

}