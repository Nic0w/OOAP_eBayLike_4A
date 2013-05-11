package fr.esiea.ooa.ebaylike.api.event;

import fr.esiea.ooa.ebaylike.api.Bid;


public interface BuyerBidListener {

	public void reservePriceReached(Bid b);
	
	public void bidCancelled(Bid b);
	
	public void higherOfferAdded(Bid b, float newOfferPrice);
	
}