package nxt.server.resource.impl;

import nxt.server.resource.GenericResource;
import nxt.server.resource.NotSupportedActionException;
import nxt.server.resource.PropertyDescriptor;
import nxt.server.resource.ResourceInfo;
import nxt.shared.json.JSONObject;
import lejos.nxt.Motor;

/**
 * risorsa motore
 * 
 * @author marco
 * 
 */
public class MotorResource extends GenericResource implements ResourceInfo {

	private Motor motor;

	public MotorResource(String resourceName, Motor motor) {
		super(resourceName);
		this.motor = motor;
		// inizializza il contenuto della propertyMap
		init();
	}
	
	/*
	 * definisce il comportamento di questa risorsa
	 */
	private void init() {
		propertyMap.put("forward", new PropertyDescriptor() {
			@Override
			public void setPropertyValue(Object value) {
				if ((Boolean) value) {
					motor.forward();
				} else {
					motor.stop();
				}
			}

			@Override
			public Object getPropertyValue() {
				return new Boolean(motor.isForward());
			}
		});
		propertyMap.put("stop", new PropertyDescriptor() {
			@Override
			public void setPropertyValue(Object value) {
				if ((Boolean) value) {
					motor.stop();
				} else {
					motor.forward();
				}
			}

			@Override
			public Object getPropertyValue() {
				return new Boolean(motor.isStopped());
			}
		});
		propertyMap.put("speed", new PropertyDescriptor() {
			@Override
			public void setPropertyValue(Object value) {
				motor.setSpeed((Integer) value);
			}

			@Override
			public Object getPropertyValue() {
				return new Integer(motor.getSpeed());
			}
		});
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
