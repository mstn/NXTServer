package nxt.server.resource;

import nxt.shared.json.JSONObject;

/**
 * astrazione per una risorsa del NXT
 * esempio: motore, sensore colore e via dicendo
 * @author marco
 *
 */
public interface Resource {

	/**
	 * ritorna una rappresentazione per la risorsa 
	 * @return rappresentazione JSON
	 */
	public JSONObject read();
	/**
	 * aggiorna lo stato della risorsa
	 * @param oggetto json che rappresenta il nuovo stato
	 */
	public void update(JSONObject representation);
	/**
	 * inserisce una nuova risorsa figlia
	 * @param una rappresentazione della risorsa
	 */
	public void create(JSONObject state);
	/**
	 * cancella la risorsa
	 */
	public void delete();
	/**
	 * ritorna il nome della risorsa
	 * @return
	 */
	public String getResourceName() ;

	/**
	 * ritorno il nome completo della risorsa che identifica la risorsa
	 * @return
	 */
	public String getResourceFullName();
	
	/**
	 * imposta il parent per questa risorsa
	 */
	public void setParent(Resource resource);
	
	/**
	 * ottiene la risorsa padre
	 */
	public Resource getParent();
	/**
	 * aggiunge un listener dello stato di questa risorsa
	 */
	public void addResourceListener(ResourceListener l);
	/**
	 * forza la notificazione dei listeners
	 * @param state
	 */
	public void notifyListeners(JSONObject state);
	
}
