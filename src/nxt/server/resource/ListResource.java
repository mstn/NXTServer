package nxt.server.resource;

import lejos.nxt.Button;
import nxt.shared.json.JSONObject;

/**
 * definisce una risorsa composta da una lista di altre risorse dette sottorisorse
 * se il nome della risorsa e' /motor/, le sottorisorse hanno nome /motor/A, /motor/B, /motor/C
 * 
 */
public class ListResource extends GenericResource implements ResourceInfo{
	
	public ListResource(String resourceName) {
		super(resourceName);
	}
	
	/**
	 * aggiunge una risorsa alla lista delle risorse
	 * @param resource
	 */
	public void add(final Resource resource){
		resource.setParent(this);
		String propertyName = resource.getResourceName();
		propertyMap.put(propertyName, new PropertyDescriptor() {		
			@Override
			public void setPropertyValue(Object value) {
				resource.update( (JSONObject) value);	
			}
			@Override
			public Object getPropertyValue() {
				return resource.read();
			}
		});
	}
	
	public void remove(BehaviorResource behaviorResource) {
		//TODO Hashtable non ha un metodo per rimuovere oggetti
	}

	/**
	 * permette di creare una risorsa di tipo behavior data una rappresentazione della stessa
	 */
	@Override
	public void create(JSONObject resource) {
		// creo la risorsa behavior
		BehaviorResource behavior = ResourceUtils.createBehaviourResourceFromJSON(resource);
		// imposto la posizione della risorsa nella gerarchia
		behavior.setParent(this);
		this.add(behavior);
		// registra la risorsa
		ResourceManager.registerResource(behavior.getResourceFullName(), behavior);	
	}

	@Override
	public void delete() {
		throw new NotSupportedActionException("delete not supported for " + getResourceName());		
	}





}
