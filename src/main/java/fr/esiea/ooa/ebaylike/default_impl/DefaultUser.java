/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Offer;
import fr.esiea.ooa.ebaylike.api.PersistentUser;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent2;

/**
 * @author Nicolas Remi Romain
 *
 */
public class DefaultUser extends PersistentUser {

	private static final Logger duLogger = LoggerFactory.getLogger(DefaultUser.class);
	
	/**
	 * @param bidStorage
	 * @param login
	 * @param firstname
	 * @param lastname
	 */
	public DefaultUser(PersistenceAgent2 bidStorage, BidFactory bidFactory, OfferFactory offerFactory,  String login, String firstname, String lastname) {
		super(bidStorage, bidFactory,  offerFactory, login, firstname, lastname);

	}

	@Override
	public void testReservPrice() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void receivedNewOffer(Bid bid, Offer newOffer) {
		duLogger.info("Seller '{}' received a new offer on bid {} by '{}'.", this.getLogin(), bid, ((User)newOffer.getEmitter()).getLogin());
	}

	@Override
	public void reservePriceReached(Bid b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bidCancelled(Bid b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void higherOfferAdded(Bid b, float newOfferPrice) {
		// TODO Auto-generated method stub
		
	}

}
