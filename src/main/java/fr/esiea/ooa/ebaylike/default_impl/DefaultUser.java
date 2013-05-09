/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.PersistentUser;
import fr.esiea.ooa.ebaylike.api.event.BidAlert;
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
	public DefaultUser(PersistenceAgent bidStorage, String login, String firstname, String lastname) {
		super(bidStorage, login, firstname, lastname);

		
		
	}


	@Override
	public void testReservPrice() {
		// TODO Auto-generated method stub

	}


	@Override
	public void publishBid(Bid bid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bidStateChanged(BidAlert alert) {
		// TODO Auto-generated method stub

	}


	@Override
	public void bid(Bid bid, float price) {
		// TODO Auto-generated method stub
		
	}

}
