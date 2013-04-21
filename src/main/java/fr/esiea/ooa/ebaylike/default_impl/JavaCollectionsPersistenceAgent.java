/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;



import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.Persistor;

/**
 * @author nic0w
 *
 */
public class JavaCollectionsPersistenceAgent implements PersistenceAgent {

	private final Persistor<User, UserFactory> userPersistor;
	private final Persistor<Bid, BidFactory> bidPersistor;
	/**
	 * 
	 */
	public JavaCollectionsPersistenceAgent() {
		
		this.bidPersistor = new MapBidPersistor();
		
		UserFactory userFactory = new DefaultUserFactory(this.bidPersistor);
		
		this.userPersistor = new MapUserPersistor(userFactory);
		
	}

	@Override
	public Persistor<User, UserFactory> getUserPersistor() {
		return this.userPersistor;
	}

	@Override
	public Persistor<Bid, BidFactory> getBidPersistor() {
		return this.bidPersistor;
	}

}
