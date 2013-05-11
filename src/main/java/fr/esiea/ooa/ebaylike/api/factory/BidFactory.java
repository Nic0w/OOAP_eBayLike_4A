/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.factory;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.Seller;

/**
 * @author nic0w
 *
 */
public interface BidFactory {

	public Bid createBid(Seller s, Product p, Date limit);
	
	public Bid createBid(Seller s, Product p, Date limit, float minPrice);

	public Bid createBid(Seller s, Product p, Date limit, float minPrice, float reservePrice);
}
