package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent2;

public class PersistentOfferManager implements OfferManager {

	private final PersistenceAgent2 storage;
	
	public PersistentOfferManager(PersistenceAgent2 storage2) {
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
