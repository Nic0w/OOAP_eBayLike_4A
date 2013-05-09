/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author nic0w
 *
 */
public class PersistentBid extends AbstractBid {

	private final PersistenceAgent storage;
	
	/**
	 * @param seller
	 * @param product
	 * @param limit
	 */
	public PersistentBid(PersistenceAgent storage, OfferFactory offer, Seller seller, Product product, Date limit) {
		super(seller, product, limit);
	
		this.storage = storage;
		
		storage.store(product.getID(), new LinkedList<Offer>());
	}


	@Override
	public void addOffer(Offer o) {
		
		LinkedList<Offer> offers = this.storage.get(LinkedList.class, this.getProduct().getID());
		
		offers.add(o);

	}


	@Override
	public float getLastOfferPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

}
