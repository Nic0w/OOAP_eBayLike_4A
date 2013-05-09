package fr.esiea.ooa.ebaylike.default_impl;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.AbstractBid;
import fr.esiea.ooa.ebaylike.api.Offer;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.Seller;

/**
 * @author Nicolas Remi Romain
 *
 */
public class DefaultBid extends AbstractBid {

	protected DefaultBid(Seller seller, Product product, Date limit) {
		super(seller, product, limit);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addOffer(Offer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getLastOfferPrice() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	

}
