package org.twiliofaces.test.twilioform.service;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

import org.twiliofaces.request.TwilioSmsSender;
import org.twiliofaces.test.twilioform.model.SmsToSend;

import com.twilio.sdk.TwilioRestException;

@Stateless
@Startup
public class SmsTimerService {

	@Inject
	TwilioSmsSender twilioSmsSender;

	@Resource
	TimerService timerService;

	public void createTimer(SmsToSend smsToSend) {
		System.out.println(smsToSend);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(smsToSend.getWhen());
		timerService.createCalendarTimer(
				new ScheduleExpression()
						.hour(calendar.get(Calendar.HOUR_OF_DAY))
						.minute(calendar.get(Calendar.MINUTE))
						.second(calendar.get(Calendar.SECOND)),
				new TimerConfig(smsToSend, true));
	}

	@Timeout
	public void timeout(Timer timer) {
		System.out.println(getClass().getName() + ": " + new Date());
		SmsToSend smsToSend = (SmsToSend) timer.getInfo();
		try {
			String smsId = twilioSmsSender
					.setAccountSid(smsToSend.getAccountSid())
					.setAuthToken(smsToSend.getTwilioToken())
					.setFrom(smsToSend.getFrom()).setTo(smsToSend.getTo())
					.setBody(smsToSend.getText()).send();
			System.out.println("sms id: " + smsId);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
