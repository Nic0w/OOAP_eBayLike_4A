/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.persistence.Persistor;

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
	public PersistentBid(Persistor<Offer, OfferFactory> offerPersistor, Seller seller, Product product, Date limit) {
		super(seller, product, limit);
		
	}


	@Override
	public void addOffer(Offer o) {
		// TODO Auto-generated method stub

	}

}
