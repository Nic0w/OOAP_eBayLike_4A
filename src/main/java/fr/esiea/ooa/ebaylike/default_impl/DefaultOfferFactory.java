package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.Offer;
import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;

public class DefaultOfferFactory implements OfferFactory {

	@Override
	public Offer createNewOffer(Bid bid,Buyer buyer, float price) {
		
		return new DefaultOffer(bid, buyer, price);
	}

}
