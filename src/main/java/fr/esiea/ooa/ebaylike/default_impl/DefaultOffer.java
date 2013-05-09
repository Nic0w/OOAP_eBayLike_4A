package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.AbstractOffer;
import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.Offer;


public class DefaultOffer extends AbstractOffer {

	
	public float getPrice() {
		return this.price;

	}

	@Override
	public Buyer getEmitter() {
		// TODO Auto-generated method stub
		return null;
	}


}
