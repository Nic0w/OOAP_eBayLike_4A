package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.Offer;


/**
 * @author Nicolas Remi Romain
 *
 */
public class DefaultOffer implements Offer {

	private final Bid bid;
	private final Buyer emitter;
	private final float price;
	
	public DefaultOffer(Bid bid, Buyer buyer, float price) {
		this.emitter = buyer;
		this.price = price;
		this.bid = bid;
	}
	
	public float getPrice() {
		return this.price;
	}

	@Override
	public Buyer getEmitter() {
		return this.emitter;
	}

	@Override
	public Bid getBid() {
		return this.bid;
	}
	
	
}
