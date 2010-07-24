package nxt.server.resource.impl;

import nxt.server.resource.GenericResource;
import nxt.server.resource.NotSupportedActionException;
import nxt.server.resource.NotWritablePropertyException;
import nxt.server.resource.PropertyDescriptor;
import nxt.server.resource.ResourceInfo;
import nxt.shared.json.JSONObject;
import lejos.nxt.addon.ColorSensor;


public class ColorSensorResource extends GenericResource implements ResourceInfo{

	
	@SuppressWarnings("unused")
	private ColorSensor sensor;
	
	public ColorSensorResource(String resourceName, ColorSensor sensor){
		super(resourceName);
		this.sensor = sensor;
		init();
	}
	


	private void init(){
		propertyMap.put("red", new PropertyDescriptor() {
			
			@Override
			public void setPropertyValue(Object value) {
				throw new NotWritablePropertyException("red is not a writable property of " + getResourceName());
			}
			
			@Override
			public Object getPropertyValue() {
				return new Integer(100);
			}
		});
		propertyMap.put("blue", new PropertyDescriptor() {
			
			@Override
			public void setPropertyValue(Object value) {
				throw new NotWritablePropertyException("blue is not a writable property of " + getResourceName());
			}
			
			@Override
			public Object getPropertyValue() {
				return new Integer(100);
			}
		});
		propertyMap.put("green", new PropertyDescriptor() {
			
			@Override
			public void setPropertyValue(Object value) {
				throw new NotWritablePropertyException("green is not a writable property of " + getResourceName());
			}
			
			@Override
			public Object getPropertyValue() {
				return new Integer(100);
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
