package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.event.BuyerBidListener;

/**
 * 
 * this interface declare method that will be used only by buyer
 * 
 * @author Nicolas Remi Romain
 *
 *
 */
public interface Buyer extends BuyerBidListener {

	/**
	 * 
	 * use this method to make an offer on a Bid
	 * 
	 * @param bid
	 * @param price
	 */
	public void bid(Bid bid, float price);	
	
	/**
	 * 
	 * a buyer can subscribe to the alerts he want with this method
	 * 
	 * @param bid
	 * @param alerts
	 */
	public void subscribeToAlerts(Bid bid, AlertType ...alerts);
	
}
