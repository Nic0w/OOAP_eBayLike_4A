/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;

/**
 * @author nic0w
 *
 */
public class DefaultBidFactory implements BidFactory {

	/**
	 * 
	 */
	public DefaultBidFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bid createBid(Product p, Date limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bid createBid(Product p, Date limit, float minPrice) {
		// TODO Auto-generated method stub
		return null;
	}



}
