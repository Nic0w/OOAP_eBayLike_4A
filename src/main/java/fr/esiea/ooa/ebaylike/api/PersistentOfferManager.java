package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

public class PersistentOfferManager implements OfferManager {

	private final PersistenceAgent storage;
	
	public PersistentOfferManager(PersistenceAgent storage2) {
		this.storage = storage2;
	}

	@Override
	public void storeOffer(Offer o) {
		storage.store(o);
	}

	@Override
	public Offer getLastOffer() {
		
		//storage.get(Offer.class).lastRow();
		
		
		return null;
	}

	@Override
	public Offer getLastOffer(Buyer b) {
		// TODO Auto-generated method stub
		return null;
	}
}
