package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.Offer;


/**
 * @author Nicolas Remi Romain
 *
 */
public class DefaultOffer implements Offer {

	private final Buyer emitter;
	private final float price;
	
	DefaultOffer(Buyer b, float price) {
		this.emitter = b;
		this.price = price;
	}
	
	public float getPrice() {
		return this.price;
	}

	@Override
	public Buyer getEmitter() {
		return this.emitter;
	}
}
