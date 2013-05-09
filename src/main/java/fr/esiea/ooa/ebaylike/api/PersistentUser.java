/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author Nicolas Remi Romain
 *
 */
public abstract class PersistentUser extends AbstractUser {

	private final PersistenceAgent bidStorage;
	
	private final BidFactory bidFactory;
	
	/**
	 * @param bidStorage
	 * @param login
	 * @param firstname
	 * @param lastname
	 */
	protected PersistentUser(PersistenceAgent bidStorage, BidFactory bidFactory, String login, String firstname, String lastname) {
		super(login, firstname, lastname);
		
		this.bidStorage = bidStorage;
		this.bidFactory = bidFactory;
	}

	@Override
	public final Bid createBid(Product p, Date limit) {
		return this.createBid(p, limit, 0);
	}

	@Override
	public final Bid createBid(Product p, Date limit, float minPrice) {
		
		Bid newBid = this.bidFactory.createBid(p, limit, minPrice);
		
		this.bidStorage.store(p.getID(), newBid);
		
		return newBid;
	}

}
