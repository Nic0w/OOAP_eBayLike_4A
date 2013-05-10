/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl.factory;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.default_impl.DefaultBid;

/**
 * @author nic0w
 *
 */
public class DefaultBidFactory implements BidFactory {

	private final PersistenceAgent offerStorage;

	public DefaultBidFactory(PersistenceAgent storage) {
		this.offerStorage = storage;
	}

	@Override
	public Bid createBid(Seller s, Product p, Date limit) {
		return new DefaultBid(this.offerStorage, s, p, limit);
	}

	@Override
	public Bid createBid(Seller s, Product p, Date limit, float minPrice) {
		return this.createBid(s, p, limit).
						setMinimumPrice(s, minPrice);
	}

	@Override
	public Bid createBid(Seller s, Product p, Date limit, float minPrice, float reservePrice) {
		return this.createBid(s, p, limit, minPrice).
						setReservePrice(s, reservePrice);
	}
}
