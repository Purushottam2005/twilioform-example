package org.twiliofaces.test.twilioform.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.annotations.configuration.TwilioNumber;
import org.twiliofaces.annotations.configuration.TwilioSid;
import org.twiliofaces.annotations.configuration.TwilioToken;
import org.twiliofaces.request.TwilioCaller;
import org.twiliofaces.request.TwilioSmsSender;
import org.twiliofaces.test.twilioform.model.CallToMake;
import org.twiliofaces.test.twilioform.model.SmsToSend;
import org.twiliofaces.test.twilioform.service.CallerTimerService;
import org.twiliofaces.test.twilioform.service.SmsTimerService;

import com.twilio.sdk.TwilioRestException;

@Named
@SessionScoped
public class SenderController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	TwilioSmsSender twilioSmsSender;

	@Inject
	TwilioCaller twilioCaller;

	@Inject
	@TwilioSid
	String accountSid;

	@Inject
	@TwilioToken
	String twilioToken;

	@Inject
	@TwilioNumber
	String from;

	@Inject
	CallerTimerService callerTimerService;

	@Inject
	SmsTimerService smsTimerService;

	private boolean sms;
	private boolean now;

	private SmsToSend smsToSend;
	private CallToMake callToMake;

	public SenderController() {
		// TODO Auto-generated constructor stub
	}

	public void schedule() {
		if (sms) {
			if (now) {
				try {
					String smsId = twilioSmsSender.setAccountSid(accountSid)
							.setAuthToken(twilioToken).setFrom(from)
							.setTo(getSmsToSend().getTo())
							.setBody(getSmsToSend().getText()).send();
					System.out.println("sms id: " + smsId);
				} catch (TwilioRestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				smsTimerService.createTimer(getSmsToSend());
			}
		} else {
			if (now) {
				try {
					String callId = twilioCaller.setAccountSid(accountSid)
							.setAuthToken(twilioToken).setFrom(from)
							.setTo(getCallToMake().getTo())
							.setEndpoint(getCallToMake().getUrl()).call();
					System.out.println("call id: " + callId);
				} catch (TwilioRestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				callerTimerService.createTimer(getCallToMake());
			}
		}
	}

	public void reset() {
		this.smsToSend = null;
		this.callToMake = null;
	}

	public SmsToSend getSmsToSend() {
		if (smsToSend == null)
			this.smsToSend = new SmsToSend();
		return smsToSend;
	}

	public void setSmsToSend(SmsToSend smsToSend) {
		this.smsToSend = smsToSend;
	}

	public CallToMake getCallToMake() {
		if (callToMake == null)
			this.callToMake = new CallToMake();
		return callToMake;
	}

	public void setCallToMake(CallToMake callToMake) {
		this.callToMake = callToMake;
	}

	public boolean isSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public boolean isNow() {
		return now;
	}

	public void setNow(boolean now) {
		this.now = now;
	}
}
