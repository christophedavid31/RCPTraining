package com.altran.rental.ui.palettes;

import org.eclipse.jface.viewers.IColorProvider;

public class Palette {
	String id, name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	IColorProvider provider;
	
	public IColorProvider getProvider() {
		return provider;
	}
	public void setProvider(IColorProvider provider) {
		this.provider = provider;
	}
}
