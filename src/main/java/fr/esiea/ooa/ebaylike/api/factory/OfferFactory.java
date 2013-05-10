/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.factory;

import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.Offer;

/**
 * @author nic0w
 *
 */
public interface OfferFactory {

	public Offer createNewOffer(Buyer b, float price);
	
}
