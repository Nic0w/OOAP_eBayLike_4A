package fr.esiea.ooa.ebaylike.core;

import fr.esiea.ooa.ebaylike.iface.Product;


public final class RealProduct implements Product {

	private final String id;
	
	private String description;
	
	RealProduct(String id, String desc) {
		this.id = id;
		this.description = desc;
	}
	
	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String text) {
		this.description = text;
	}
}
