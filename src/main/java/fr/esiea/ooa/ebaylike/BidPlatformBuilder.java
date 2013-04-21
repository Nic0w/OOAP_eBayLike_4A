/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;


/**
 * @author nic0w
 *
 */
public class BidPlatformBuilder {

	private final PersistenceAgent persistenceAgent;
	
	/**
	 * 
	 */
	public BidPlatformBuilder(PersistenceAgent agent) {
		this.persistenceAgent = agent;
	}

	
	public BidPlatform build() {
		return new BidPlatform(this.persistenceAgent);
	}
	
}
