/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.event.BidAlert;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.persistence.Persistor;

/**
 * @author nic0w
 *
 */
public abstract class PersistentUser extends AbstractUser {

	private final Persistor<Bid, BidFactory> bidPersistor;
	
	
	/**
	 * @param persistor
	 * @param login
	 * @param firstname
	 * @param lastname
	 */
	protected PersistentUser(Persistor<Bid, BidFactory> persistor, String login, String firstname, String lastname) {
		super(login, firstname, lastname);
		
		this.bidPersistor = persistor;
	}

	@Override
	public final Bid createBid(Product p, Date limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Bid createBid(Product p, Date limit, float minPrice) {
		// TODO Auto-generated method stub
		return null;
	}

}
