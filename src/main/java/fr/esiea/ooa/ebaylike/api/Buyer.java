package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.event.BuyerBidListener;

public interface Buyer extends BuyerBidListener {

	public void bid(Bid bid, float price);	
	
	public void testReservPrice();	
	
	public void subscribeToAlerts(Bid bid, AlertType ...alerts);
	
}
