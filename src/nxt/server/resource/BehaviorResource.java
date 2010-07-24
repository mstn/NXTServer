package nxt.server.resource;

import java.util.Enumeration;


import nxt.server.NotYetImplementedException;
import nxt.shared.json.JSONObject;

public class BehaviorResource extends GenericResource implements
		ResourceListener {

	private Resource trigger;
	private Resource target;
	private JSONObject behavior;
	private JSONObject condition;

	public BehaviorResource(String resourceName, Resource trigger,
			Resource target) {
		super(resourceName);
		this.trigger = trigger;
		this.target = target;
		// metto behavior in ascolto di quello che succede in trigger
		this.trigger.addResourceListener(this);

	}

	@Override
	public void create(JSONObject state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		throw new NotYetImplementedException("delete not yet implemented");
		// rimuovo il behavior dalla lista
		/// ListResource parent = (ListResource) getParent();
		/// parent.remove(this);
		// cacello il behavior dal registro
		/// ResourceManager.unregisterResource(this);
	}

	@Override
	public void stateChanged(JSONObject state) {
		boolean updateTarget = true;
		// se la condizione corrisponde allo stato del trigger
		Enumeration keys = condition.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			Object value = condition.get(key);
			// ottengo il valore corripondente nello state
			Object stateValue = state.get(key);
			if (stateValue != null) {
				if (!value.equals(stateValue)) {
					updateTarget = false;
					break;
				}
			} else {
				updateTarget = false;
				break;
			}
		}

		if (updateTarget) {
			// aggiorno lo status del target
			target.update(behavior);
		}
	}

	public void setBehavior(JSONObject behavior) {
		this.behavior = behavior;
	}

	public void setCondition(JSONObject condition) {
		this.condition = condition;
	}

}
