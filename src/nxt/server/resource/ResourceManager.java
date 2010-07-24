package nxt.server.resource;

import java.util.Hashtable;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import nxt.server.resource.impl.MotorResource;
import nxt.server.resource.impl.TouchSensorResource;

/**
 * gestore delle risorse e delle metainformazioni associate
 * @author marco
 *
 */
public class ResourceManager {
	
	// mapping tra nome della risorsa e la ResourceInfo
	private final static Hashtable resourceInfoMap;
	
	// inizializzazione statica delle risorse
	// TODO rendere dimamico con inizializzazione risorse runtime in base alla loro disponibilita' su particolare NXT
	static{
		resourceInfoMap = new Hashtable();
		
		// allocazione delle risorse ad albero
		
		// motori
		Resource motorA = new MotorResource("A", Motor.A);
		Resource motorB = new MotorResource("B", Motor.B);
	    Resource motorC = new MotorResource("C", Motor.C);
		ListResource motorList = new ListResource("motor");
		motorList.add( motorA );
		motorList.add( motorB );
		motorList.add( motorC );
		
		// sensori (controllare che il sensore sia attaccato nella porta giusta!)
		Resource touchSensor1 = new TouchSensorResource("touch1", SensorPort.S1);
		Resource touchSensor2 = new TouchSensorResource("touch2", SensorPort.S2);
		ListResource sensorList = new ListResource("sensor");
		sensorList.add(touchSensor1);
		sensorList.add(touchSensor2);
		
		
		// tutto
		ListResource root = new ListResource("/");
		root.add(motorList);
		root.add(sensorList);
		
		// mapping 
		resourceInfoMap.put(root.getResourceFullName(), root );
		
		resourceInfoMap.put(motorList.getResourceFullName(), motorList);
		resourceInfoMap.put(motorA.getResourceFullName(), motorA );
		resourceInfoMap.put(motorB.getResourceFullName(), motorB );
		resourceInfoMap.put(motorC.getResourceFullName(), motorC );
		
		resourceInfoMap.put(sensorList.getResourceFullName(), sensorList);
		resourceInfoMap.put(touchSensor1.getResourceFullName(), touchSensor1);
		resourceInfoMap.put(touchSensor2.getResourceFullName(), touchSensor2);
		
		System.out.println(resourceInfoMap);
	}


	/**
	 * permette di ottenere le meta informazioni su una risorsa conoscendone il nome
	 * @param resourceName nome della risorsa
	 * @return le metainformazioni sulla risorsa
	 */
	public static ResourceInfo getResourceInfo(String resourceName){
		return (ResourceInfo) resourceInfoMap.get(resourceName);
	}


	public static void registerResource(String resourceFullName,
			BehaviorResource behavior) {
		resourceInfoMap.put(resourceFullName, behavior);		
	}


	public static void unregisterResource(BehaviorResource behaviorResource) {
		// TODO Hashtable non implementa la delete nella versione corrente di LeJOS
		/// String resourceName = behaviorResource.getFullName();
		/// resourceInfoMap.delete(resourceName);
	}
	
}
