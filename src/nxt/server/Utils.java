package nxt.server;

import nxt.shared.json.JSONObject;
import nxt.shared.json.JSONParseException;
import nxt.shared.json.JSONParser;

public class Utils {
	
	public static Request getRequestFromJSON(String json) throws JSONParseException {
		JSONObject obj = JSONParser.parse(json);
		Request request = new Request();
		request.setAction( (String)obj.get("action") );
		request.setResource( (String)obj.get("resource") );
		Object payload = obj.get("payload");
		if (payload != null ){
			// System.out.println(payload);
			// Button.waitForPress();
			request.setPayload( (JSONObject) payload);
		}
		return request;
	}

	public static final String getJSONFromResponse(Response response){
		System.out.println("Build response");
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":").append(response.getStatus());
		if (response.getStatus() == Response.OK && response.getContent() != null){
			sb.append(",\"content\":").append(response.getContent());
		} else if (response.getStatus() != Response.OK){
			sb.append(",\"error\":").append("\"").append(response.getMessage()).append("\"");
		}
		sb.append("}\n");
		return sb.toString();
	}

	public static String createJSONErrorResponse(String message) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":").append(Response.INT_ERROR);
		sb.append(",\"error\":").append("\"").append(message).append("\"");
		sb.append("}\n");
		return sb.toString();
	}
	
}
