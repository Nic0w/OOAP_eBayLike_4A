package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.Product;


public final class DefaultProduct implements Product {

	private String id;
	
	private String description;
	
	DefaultProduct(String id, String desc) {
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
	public Product setDescription(String text) {
		this.description = text;		
		return this;
	}
	
	public Product setID(String identifiant){
		this.id = identifiant;		
		return this;
	}
}
