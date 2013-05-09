/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.event.BidAlert;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author nic0w
 *
 */
public abstract class PersistentUser extends AbstractUser {

	private final PersistenceAgent bidStorage;
	
	
	/**
	 * @param bidStorage
	 * @param login
	 * @param firstname
	 * @param lastname
	 */
	protected PersistentUser(PersistenceAgent bidStorage, String login, String firstname, String lastname) {
		super(login, firstname, lastname);
		
		this.bidStorage = bidStorage;
		
	}

	@Override
	public final Bid createBid(Product p, Date limit) {
		
		
		
		return null;
	}

	@Override
	public final Bid createBid(Product p, Date limit, float minPrice) {
		// TODO Auto-generated method stub
		return null;
	}

}
