/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.event.AlertType;

/**
 * @author nic0w
 *
 */
public interface OfferManager {

	public void storeOffer(Offer o);
	
	public Offer getLastOffer();
	
	public Offer getLastOffer(Buyer b);
	
}
