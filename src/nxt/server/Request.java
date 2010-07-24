package nxt.server;

import nxt.shared.json.JSONObject;


public class Request {

	private String action;
	private String resource;
	private JSONObject payload;


	public void setAction(String action) {
		this.action = action;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getAction() {
		return action;
	}

	public String getResource() {
		return resource;
	}

	public void setPayload(JSONObject payload) {
		this.payload = payload;
	}

	public JSONObject getPayload() {
		return payload;
	}

}
