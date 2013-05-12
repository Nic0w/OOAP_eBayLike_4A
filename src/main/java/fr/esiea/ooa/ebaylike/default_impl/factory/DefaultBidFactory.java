/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl.factory;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.PersistentAlertManager;
import fr.esiea.ooa.ebaylike.api.PersistentBid;
import fr.esiea.ooa.ebaylike.api.PersistentOfferManager;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.api.factory.AlertFactory;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;


/**
 * @author nic0w
 *
 */
public class DefaultBidFactory implements BidFactory {
	
	private final PersistentAlertManager alertManager;
	private final PersistentOfferManager offerManager;
	
	public DefaultBidFactory(PersistenceAgent storage, AlertFactory factory) {
		
		this.alertManager = new PersistentAlertManager(storage, factory);
		
		this.offerManager = new PersistentOfferManager(storage);
		
	}

	@Override
	public Bid createBid(Seller seller, Product product, Date limit) {
		
		return new PersistentBid(
					this.offerManager,
					this.alertManager,
					seller, 
					product, 
					limit
				);
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
