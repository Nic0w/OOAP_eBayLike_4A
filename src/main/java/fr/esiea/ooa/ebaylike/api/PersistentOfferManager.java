package fr.esiea.ooa.ebaylike.api;

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
	public Offer getLastOffer(Bid b) {
		
		return this.storage.get(Offer.class).where(Bid.class).isEqualTo(b).lastRow();
	}

	@Override
	public Offer getLastOffer(Buyer b) {
		// TODO Auto-generated method stub
		return null;
	}
}
