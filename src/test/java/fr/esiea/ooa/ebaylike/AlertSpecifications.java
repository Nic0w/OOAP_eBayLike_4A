/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Buyer;
import fr.esiea.ooa.ebaylike.api.DateHelper;
import fr.esiea.ooa.ebaylike.api.Offer;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;
import fr.esiea.ooa.ebaylike.default_impl.DefaultOffer;
import fr.esiea.ooa.ebaylike.default_impl.factory.DefaultAlertFactory;
import fr.esiea.ooa.ebaylike.default_impl.factory.DefaultBidFactory;
import fr.esiea.ooa.ebaylike.default_impl.persistence.CollectionsDatabase;

/**
 * @author nic0w
 *
 */
public class AlertSpecifications {

	static final Logger asLogger = LoggerFactory.getLogger(AlertSpecifications.class);
	
	/**
	 * Test if the seller automatically retrieve an alert when an offer is added to one of his bids.
	 * 
	 * @throws UserAlreadyExistsException
	 * @throws StorageException
	 */
	@Test
	public void testAutomaticAlertOnSeller() throws UserAlreadyExistsException, StorageException {
		
		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Date limit = DateHelper.getTomorrowSameHour();
		Product p = platform.newProduct("test");
		
		
		FakeSeller fakeSeller = new FakeSeller();
		
		
		Buyer buyer  = platform.newUser("aRandomLogin2", "Benjamin2", "Franklin2");

		CollectionsDatabase storage = new CollectionsDatabase();
		storage.bind(DefaultOffer.class, Offer.class);
		
		DefaultBidFactory bidFactory = new DefaultBidFactory(storage, new DefaultAlertFactory());
		
		Bid bid =  bidFactory.createBid(fakeSeller, p, limit).publishIt(fakeSeller);
		
		buyer.bid(bid, 5);
		
		Offer lastOffer = storage.get(Offer.class).where(Bid.class).isEqualTo(bid).firstRow();
		
		assertThat(fakeSeller.receivedBid, is(bid));
		assertThat(fakeSeller.receivedOffer, is(lastOffer));
	}
}



class FakeSeller implements Seller {

	public Bid receivedBid;
	public Offer receivedOffer;
	
	@Override
	public void receivedNewOffer(Bid bid, Offer newOffer) {
		
		AlertSpecifications.asLogger.info("Hello from FakeSeller.receivedNewOffer() !");
		
		this.receivedBid = bid;
		this.receivedOffer = newOffer;
	}

	@Override
	public Bid createBid(Product p, Date limit) { return null; }

	@Override
	public Bid createBid(Product p, Date limit, float minPrice) { return null; }

	@Override
	public void publishBid(Bid bid) {}
}

