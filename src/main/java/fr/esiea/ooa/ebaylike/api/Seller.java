package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.event.SellerBidListener;


/**
 * @author Nicolas Remi Romain
 *
 */
public interface Seller extends SellerBidListener {

	/**
	 * 
	 * call this method to create a Bid with a minimum Price 
	 * 
	 * @param p
	 * @param limit
	 * @return
	 */
	public Bid createBid(Product p, Date limit);
	
	/**
	 * 
	 * 

	 * call this method to create a Bid with a minimum Price and a Limit Date
	 * 
	 * @param p
	 * @param limit
	 * @param minPrice
	 * @return
	 */
	public Bid createBid(Product p, Date limit, float minPrice);
	
	/**
	 * 
	 * call this method to published a Bid
	 * 
	 * @param bid
	 * @return
	 */
	public Bid publishBid(Bid bid);

}
