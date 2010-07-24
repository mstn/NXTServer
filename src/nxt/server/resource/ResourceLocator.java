package nxt.server.resource;


/**
 * permette di ottenere una risorsa 
 * 
 * @author marco
 *
 */
public class ResourceLocator {

	/**
	 * permette di ottenere la referenza ad una risorsa conoscendo il nome
	 * @param resourceName il nome (uri) della risorsa
	 * @return la risorsa associata all'url, null se non esiste
	 */
	public static final Resource lookupResource(String resourceName){
		Resource resource = null;
		ResourceInfo info = ResourceManager.getResourceInfo(resourceName);
		if (info != null){
			resource = info.getResource();
		}
		return resource;
	}
	
}
