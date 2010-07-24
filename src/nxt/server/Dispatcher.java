package nxt.server;

import nxt.server.resource.Resource;
import nxt.server.resource.ResourceLocator;
import nxt.shared.json.JSONObject;

/**
 * Il compito di questa classe e' invocare il metodo corretto a seconda
 * dell'azione specificata nella request
 * 
 * @author marco
 * 
 */
public class Dispatcher {


	public void process(Request request, Response response) {
		System.out.println("Dispatch");
		// ottieni l'azione
		String action = request.getAction();
		// ottieni la risorsa
		String resourceName = request.getResource();
		Resource resource = ResourceLocator.lookupResource(resourceName);
		System.out.println("resource found");
		System.out.println("do " + action + " " + resourceName);
		// invoca il metodo opportuno del server
		if ("GET".equals(action)){
			// System.out.println("get");
			JSONObject obj = resource.read();
			String ret = obj.toString();
			response.setContent(ret);
			response.setStatus(Response.OK);
		} else if ("PUT".equals(action)){
			// System.out.println("put");
			JSONObject representation = request.getPayload();
			resource.update(representation);
			response.setStatus(Response.OK);
		} else if ("POST".equals(action)){
			// System.out.println("post");
			JSONObject representation = request.getPayload();
			resource.create(representation);
			response.setStatus(Response.OK);	
		} else {
			String msg = "unknown action " + action;
			response.setMessage(msg);
			response.setStatus(Response.BAD_REQUEST);
		}
	}

}
