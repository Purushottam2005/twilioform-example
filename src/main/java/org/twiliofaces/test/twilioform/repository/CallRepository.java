package org.twiliofaces.test.twilioform.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.twiliofaces.test.twilioform.model.CallToMake;

@ApplicationScoped
@Named
public class CallRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, CallToMake> calls;

	public void save(CallToMake CallToMake) {
		getCalls().put(CallToMake.getUid(), CallToMake);
	}

	public boolean exist(String uid) {
		return getCalls().containsKey(uid);
	}

	public CallToMake find(String uid) {
		if (exist(uid))
			return getCalls().get(uid);
		return null;
	}

	public Map<String, CallToMake> getCalls() {
		if (this.calls == null)
			this.calls = new ConcurrentHashMap<String, CallToMake>();
		return calls;
	}

	public void setCalls(Map<String, CallToMake> calls) {
		this.calls = calls;
	}

	@Produces
	@Named
	public List<CallToMake> getAllCalls() {
		if (getCalls() != null)
			return new ArrayList(getCalls().values());
		return new ArrayList<CallToMake>();
	}

}
