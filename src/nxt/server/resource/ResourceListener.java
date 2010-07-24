package nxt.server.resource;

import nxt.shared.json.JSONObject;

public interface ResourceListener {

	public void stateChanged(JSONObject state);
	
}
