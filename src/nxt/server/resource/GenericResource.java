package nxt.server.resource;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import nxt.shared.json.JSONObject;

/**
 * questa classe offre un'implementazione base per l'interfaccia resource
 * assume che ad una key della rappresentazione corrisponde metodi set e get per 
 * scrivere o leggere il valore della proprieta' della risorsa corrisponedente
 * @author marco
 *
 */
public abstract class GenericResource implements Resource, ResourceInfo{

	private String resourceName;
	private Resource parent;
	// mapping tra nome proprieta' e proprieta'
	protected final Hashtable propertyMap;
	@SuppressWarnings("rawtypes")
	protected ArrayList listeners;
	
	/**
	 * e' necessario indicare il nome della risorsa per 
	 * @param resourceName
	 */
	@SuppressWarnings("rawtypes")
	public GenericResource(String resourceName){
		this.resourceName = resourceName;
		this.propertyMap = new Hashtable();
		this.listeners = new ArrayList();
	}
	
	@Override
	public String getResourceName() {
		return resourceName;
	}
	@Override
	public Resource getParent() {
		return parent;
	}
	@Override
	public void setParent(Resource parent) {
		this.parent = parent;
	}
	@Override
	public String getResourceFullName() {
		if (parent == null){
			// root
			return getResourceName();
		}
		String parentFullName = parent.getResourceFullName();
		return ResourceUtils.createFullName(parentFullName, getResourceName());
	}
	
	@Override
	public JSONObject read() {
		// creo un oggetto JSON per rappresentare la risorsa
		JSONObject obj = new JSONObject();
		// ottengo info sulla risorsa
		ResourceInfo info = ResourceManager.getResourceInfo( getResourceFullName() );
		// ottengo i descrittori
		Enumeration descriptorNames = info.getPropertyDescriptorNames( );
		// passo in rassegna i descrittori e creo oggetto JSON
		while (descriptorNames.hasMoreElements()){
			String key = (String) descriptorNames.nextElement();
			PropertyDescriptor desc = info.getPropertyDescriptor(key);
			Object value = desc.getPropertyValue();
			// crea nuova coppia key/value per json object
			obj.put(key, value);
		}
		return obj;
	}

	@Override
	public void update(JSONObject representation) {
		Enumeration keys = representation.getKeys();
		while (keys.hasMoreElements()){
			String key = (String) keys.nextElement();
			Object value = representation.get(key);
			// ottengo informazioni sulla risorsa
			ResourceInfo info = ResourceManager.getResourceInfo( getResourceFullName() );
			// ottengo informazioni su la proprieta' della risorsa che e' identificata dal nome key
			PropertyDescriptor desc = info.getPropertyDescriptor( key );
			if (desc!=null){
				// imposto il valore della risorsa
				desc.setPropertyValue( value );	
			}
			// TODO se la key non e' una proprieta' della risorsa notificare il client
			
		}
	}
	
	@Override
	public Resource getResource() {
		return this;
	}

	@Override
	public PropertyDescriptor getPropertyDescriptor(String name) {
		return (PropertyDescriptor) propertyMap.get(name);
	}

	@Override
	public Enumeration getPropertyDescriptorNames() {
		return propertyMap.keys();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addResourceListener(ResourceListener l) {
		listeners.add(l);	
	}
	@Override
	public void notifyListeners(JSONObject state) {
		for (int i = 0; i < listeners.size(); i++) {
			ResourceListener l = (ResourceListener) listeners.get(i);
			l.stateChanged(state);
		}
	}

}
