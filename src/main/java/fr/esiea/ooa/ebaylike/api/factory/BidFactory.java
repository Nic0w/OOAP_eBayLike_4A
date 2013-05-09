/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.factory;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Product;

/**
 * @author nic0w
 *
 */
public interface BidFactory {

	public Bid createBid(Product p, Date limit);
	
	public Bid createBid(Product p, Date limit, float minPrice);
}
