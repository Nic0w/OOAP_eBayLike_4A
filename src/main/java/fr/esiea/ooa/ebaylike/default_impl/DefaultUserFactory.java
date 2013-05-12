/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;


import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent2;

/**
 * @author nic0w
 *
 */
public class DefaultUserFactory implements UserFactory {

	private final PersistenceAgent2 bidStorage;
	
	private final BidFactory   bidFactory;
	private final OfferFactory offerFactory;
	
	/**
	 * 
	 */
	public DefaultUserFactory(PersistenceAgent2 storage, BidFactory bidFactory, OfferFactory offerFactory) {
		this.bidStorage   = storage;
		this.bidFactory   = bidFactory;
		this.offerFactory = offerFactory;
	}
	
	@Override
	public User createNewUser(String login, String name, String forename) {
		return new DefaultUser(this.bidStorage, this.bidFactory, this.offerFactory, login, name, forename);
	}

}
