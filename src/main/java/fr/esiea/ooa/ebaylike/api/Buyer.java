package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.event.AlertType;

public interface Buyer {

	public void bid();
	
	public void testReservPrice();
	
	public void subscribeToAlerts(Bid bid, AlertType ...alerts);
	
	
}