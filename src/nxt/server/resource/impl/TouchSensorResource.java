package nxt.server.resource.impl;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import nxt.server.resource.GenericResource;
import nxt.server.resource.NotSupportedActionException;
import nxt.server.resource.NotWritablePropertyException;
import nxt.server.resource.PropertyDescriptor;
import nxt.shared.json.JSONObject;

public class TouchSensorResource extends GenericResource implements
		SensorPortListener {

	private TouchSensor sensor;
	private SensorPort port;

	public TouchSensorResource(String resourceName, SensorPort port) {
		super(resourceName);
		this.sensor = new TouchSensor(port); 
		this.port = port;
		this.port.addSensorPortListener(this);
		init();
	}

	private void init() {
		propertyMap.put("isPressed", new PropertyDescriptor() {

			@Override
			public void setPropertyValue(Object value) {
				throw new NotWritablePropertyException("isPressed is not a writable property of " + getResourceName());
			}

			@Override
			public Object getPropertyValue() {
				return new Boolean(sensor.isPressed());
			}
		});
	}

	/*
	 * questo metodo e' invocato quando un cambiamento nello stato del sensore
	 * touch e' rilevato
	 * 
	 * @see lejos.nxt.SensorPortListener#stateChanged(lejos.nxt.SensorPort, int,
	 * int)
	 */
	@Override
	public void stateChanged(SensorPort source, int arg1, int arg2) {
		if (port.getId() == source.getId()){
			boolean isPressed = sensor.isPressed();
			JSONObject state = new JSONObject();
			state.put("isPressed", isPressed);
			notifyListeners(state);	
		}
	}



	@Override
	public void create(JSONObject state) {
		throw new NotSupportedActionException("create not supported for " + getResourceName());
	}

	@Override
	public void delete() {
		throw new NotSupportedActionException("delete not supported for " + getResourceName());
	}
}
