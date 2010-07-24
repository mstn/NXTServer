package nxt.server.resource;

import nxt.shared.json.JSONObject;

public class ResourceUtils {

	private static final char RESOURCE_SEPARATOR = '/';
	
	/**
	 * crea il nome completo di una risorsa concatenando il nome del padre con quello del figlio
	 * @param parentFullName
	 * @param resourceName
	 * @return
	 */
	public static String createFullName(String parentFullName,
			String resourceName) {
		StringBuffer buffer = new StringBuffer(parentFullName);
		char lastChar = parentFullName.charAt(parentFullName.length()-1);
		if ( RESOURCE_SEPARATOR != lastChar){
			// ultimo carattere e'  non / 
			buffer.append(RESOURCE_SEPARATOR);
		} 
		buffer.append(resourceName);
		return buffer.toString();
	}
	
	/**
	 * crea una risorsa di tipo Behavior da una rappresentazione JSON
	 * @param rappresentazione della risorsa
	 * @return la risorsa, null se non e' possibile crearla 
	 */
	public static BehaviorResource createBehaviourResourceFromJSON(JSONObject jobj){
		BehaviorResource resource = null;
		
		// ottengo le informazioni che  mi servono
		String name = (String) jobj.get("name");
		JSONObject trigger = (JSONObject) jobj.get("trigger");
		JSONObject target = (JSONObject) jobj.get("target");
		
		// ottengo le risorse trigger e target
		Resource triggerResource = ResourceLocator.lookupResource( (String)trigger.get("name"));
		Resource targetResource = ResourceLocator.lookupResource( (String)target.get("name"));
		// costruisco una risorsa behavior
		resource = new BehaviorResource(name, triggerResource, targetResource);
		resource.setBehavior( (JSONObject) target.get("behavior") );
		resource.setCondition( (JSONObject) trigger.get("condition"));

		// ritorno la risorsa
		return resource;
	}
	
	
}
