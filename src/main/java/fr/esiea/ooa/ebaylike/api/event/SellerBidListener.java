package fr.esiea.ooa.ebaylike.api.event;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Offer;

public interface SellerBidListener {

	public void receivedNewOffer(Bid bid, Offer newOffer);
	
}
