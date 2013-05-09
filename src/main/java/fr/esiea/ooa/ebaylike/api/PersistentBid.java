/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author nic0w
 *
 */
public class PersistentBid extends AbstractBid {

	/**
	 * @param seller
	 * @param product
	 * @param limit
	 */
	public PersistentBid(PersistenceAgent storage, OfferFactory offer, Seller seller, Product product, Date limit) {
		super(seller, product, limit);
		
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
