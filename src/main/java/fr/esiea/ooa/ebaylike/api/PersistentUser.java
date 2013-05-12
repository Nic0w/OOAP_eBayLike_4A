/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author Nicolas Remi Romain
 *
 */
public abstract class PersistentUser extends AbstractUser {

	private final PersistenceAgent bidStorage;
	
	private final OfferFactory offerFactory;
	
	
	/**
	 * @param bidStorage2
	 * @param login
	 * @param firstname
	 * @param lastname
	 */
	protected PersistentUser(PersistenceAgent bidStorage, BidFactory bidFactory, OfferFactory offerFactory, String login, String firstname, String lastname) {
		super(bidFactory, offerFactory, login, firstname, lastname);
		
		this.bidStorage = bidStorage;

		this.offerFactory = offerFactory;
	}

	@Override
	public final Bid createBid(Product p, Date limit) {
		return this.createBid(p, limit, 0);
	}

	@Override
	public Bid createBid(Product p, Date limit, float minPrice) {
		
		Bid newBid = super.createBid(p, limit, minPrice);
		
		this.bidStorage.store(newBid);
				
		return newBid;
	}

}
