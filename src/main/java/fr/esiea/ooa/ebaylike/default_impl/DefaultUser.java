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
	public DefaultUser(PersistenceAgent bidStorage, BidFactory bidFactory, OfferFactory offerFactory,  String login, String firstname, String lastname) {
		super(bidStorage, bidFactory,  offerFactory, login, firstname, lastname);

	}

	@Override
	public void receivedNewOffer(Bid bid, Offer newOffer) {
		duLogger.info("Seller '{}' received a new offer on bid {} by '{}'.", this.getLogin(), bid, ((User)newOffer.getEmitter()).getLogin());
	}

	@Override
	public void reservePriceReached(Bid b) {
		duLogger.info("Alert for Buyer '{}' : Reserve Price reached on bid {}.", this.getLogin(), b);
	}

	@Override
	public void bidCancelled(Bid b) {
		duLogger.info("Alert for Buyer '{}' : bid {} was cancelled.", this.getLogin(), b);
	}

	@Override
	public void higherOfferAdded(Bid b, float newOfferPrice) {
		duLogger.info("Alert for Buyer '{}' : An higher offer ({}) was added on bid {}.", this.getLogin(), newOfferPrice, b);
	}

}
