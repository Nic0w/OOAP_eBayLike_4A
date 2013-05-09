package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.event.AlertType;

/**
 * 
 * this interface declare method that will be used only by buyer
 * 
 * @author Nicolas Remi Romain
 *
 *
 */
public interface Buyer {

	/**
	 * 
	 * use this method to make an offer on a Bid
	 * 
	 * @param bid
	 * @param price
	 */
	public void bid(Bid bid, float price);	
	

	public void testReservPrice();	
	
	/**
	 * 
	 * a buyer can subscribe to the alerts he want with this method
	 * 
	 * @param bid
	 * @param alerts
	 */
	public void subscribeToAlerts(Bid bid, AlertType ...alerts);
	
	
}
