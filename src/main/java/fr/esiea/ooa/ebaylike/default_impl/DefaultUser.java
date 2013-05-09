/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Offer;
import fr.esiea.ooa.ebaylike.api.PersistentUser;
import fr.esiea.ooa.ebaylike.api.event.BidAlert;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author nic0w
 *
 */
public class DefaultUser extends PersistentUser {

	/**
	 * @param bidStorage
	 * @param login
	 * @param firstname
	 * @param lastname
	 */
	public DefaultUser(PersistenceAgent bidStorage, BidFactory bidFactory, String login, String firstname, String lastname) {
		super(bidStorage, bidFactory, login, firstname, lastname);

		
		
	}

	@Override
	public void bid(Bid bid, float price) {

		//bid.addOffer(o);
		
		
	}

	@Override
	public void testReservPrice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bidStateChanged(BidAlert alert) {
		
		
			
	}

	@Override
	public void receivedNewOffer(Bid bid, Offer newOffer) {
	
	}

}
