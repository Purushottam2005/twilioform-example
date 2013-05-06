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

import org.twiliofaces.request.TwilioCaller;
import org.twiliofaces.test.twilioform.model.CallToMake;

import com.twilio.sdk.TwilioRestException;

@Stateless
@Startup
public class CallerTimerService {

	@Inject
	TwilioCaller twilioCaller;

	@Resource
	TimerService timerService;

	public void createTimer(CallToMake callToMake) {
		System.out.println(callToMake);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(callToMake.getWhen());
		timerService.createCalendarTimer(
				new ScheduleExpression()
						.hour(calendar.get(Calendar.HOUR_OF_DAY))
						.minute(calendar.get(Calendar.MINUTE))
						.second(calendar.get(Calendar.SECOND)),
				new TimerConfig(callToMake, true));
	}

	@Timeout
	public void timeout(Timer timer) {
		System.out.println(getClass().getName() + ": " + new Date());
		CallToMake callToMake = (CallToMake) timer.getInfo();
		try {
			String callId = twilioCaller
					.setAccountSid(callToMake.getAccountSid())
					.setAuthToken(callToMake.getTwilioToken())
					.setFrom(callToMake.getFrom()).setTo(callToMake.getTo())
					.setEndpoint(callToMake.getUrl()).call();
			System.out.println("call id: " + callId);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
