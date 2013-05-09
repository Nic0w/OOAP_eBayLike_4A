package fr.esiea.ooa.ebaylike.default_impl;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.AbstractBid;
import fr.esiea.ooa.ebaylike.api.Offer;
import fr.esiea.ooa.ebaylike.api.PersistentBid;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author Nicolas Remi Romain
 *
 */
public class DefaultBid extends PersistentBid {

	public DefaultBid(PersistenceAgent storage, OfferFactory offer, Seller seller, Product product, Date limit) {
		super(storage, offer, seller, product, limit);
		
		
		
		
	}

	@Override
	public void addOffer(Offer o) {
	
	}

	@Override
	public float getLastOfferPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

}
