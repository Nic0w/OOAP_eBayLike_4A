/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;


/**
 * @author nic0w
 *
 */
public interface OfferManager {

	public void storeOffer(Offer o);
	
	public Offer getLastOffer(Bid b);
	
	public Offer getLastOffer(Buyer b);
	
}
