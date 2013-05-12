/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.factory;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.Offer;

/**
 * @author nic0w
 *
 */
public interface OfferFactory {

	public Offer createNewOffer(Bid bid, Buyer b, float price);
	
}
