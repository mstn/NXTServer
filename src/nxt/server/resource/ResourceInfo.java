package nxt.server.resource;

import java.util.Enumeration;

public interface ResourceInfo {
	public Resource getResource();
	public PropertyDescriptor getPropertyDescriptor(String name);
	public Enumeration getPropertyDescriptorNames();
}
