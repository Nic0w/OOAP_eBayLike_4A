/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.persistence.Persistor;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;

/**
 * @author nic0w
 *
 */
public class MapBidPersistor implements Persistor<Bid, BidFactory> {

	/**
	 * 
	 */
	public MapBidPersistor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BidFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bid get(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(Bid o) throws StorageException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Bid o) {
		// TODO Auto-generated method stub
		
	}

}
