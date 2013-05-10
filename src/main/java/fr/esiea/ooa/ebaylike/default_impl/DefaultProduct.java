package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.Product;


/**
 * @author Nicolas Remi Romain
 *
 */
public final class DefaultProduct implements Product {

	private final String id;
	
	private String description;
	
	public DefaultProduct(String id, String desc) {
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
