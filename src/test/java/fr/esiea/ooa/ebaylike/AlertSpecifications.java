/**
 * 
 */
package fr.esiea.ooa.ebaylike;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.event.BuyerBidListener;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;
import fr.esiea.ooa.ebaylike.default_impl.DefaultOffer;
import fr.esiea.ooa.ebaylike.default_impl.factory.DefaultAlertFactory;
import fr.esiea.ooa.ebaylike.default_impl.factory.DefaultBidFactory;
import fr.esiea.ooa.ebaylike.default_impl.factory.DefaultOfferFactory;
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
	
	/**
	 * An user should be able to configure alerts.
	 * 
	 * @throws UserAlreadyExistsException
	 * @throws StorageException
	 */
	@Test
	public void testUserCanConfigureAlerts() throws UserAlreadyExistsException, StorageException {
		
		BidPlatform platform = BidPlatform.getDefaultInstance(true);

		Date limit = DateHelper.getTomorrowSameHour();
		Product product = platform.newProduct("test");
		
		Seller seller  = platform.newUser("aRandomLogin2", "Benjamin2", "Franklin2");
		
		float reservePrice = 10;
		
		Bid bid = seller.createBid(product, limit).
							setReservePrice(seller, reservePrice).
							publishIt(seller);
		
		
		DefaultOfferFactory offerFactory = new DefaultOfferFactory();
		
		FakeUser fakeUser1 = new FakeUser(offerFactory);
		FakeUser fakeUser2 = new FakeUser(offerFactory);
		
		float firstOffer = 3;
		fakeUser1.bid(bid, firstOffer);
		
		bid.registerAlertListener(fakeUser1, AlertType.HIGHER_OFFER_DONE); //We register fakeUser1 for the Alert HIGHER_OFFER_DONE
		
		float higherOffer = 4;
		//fakeUser2 add an offer high than the one from fakeUser1. fakeUser1 should receive an alert.
		fakeUser2.bid(bid, higherOffer);
		
		assertTrue(higherOffer > firstOffer);		
		assertThat(fakeUser1.receivedBid, is(bid));
		assertThat(fakeUser1.newOfferPrice, is(higherOffer));
		
		
		bid.registerAlertListener(fakeUser2, AlertType.RESERVE_PRICE_REACHED);//We register fakeUser2 for the Alert RESERVE_PRICE_REACHED
		
		float newOffer = 11;
		//fakeUser1 add an offer high than the reserve price. fakeUser2 should receive an alert.
		fakeUser1.bid(bid, newOffer);
		
		assertTrue(newOffer > reservePrice);
		assertThat(fakeUser2.receivedBid, is(bid));
		
		
		//In order to test the alert BID_CANCELLED we create a new Bid.
		Product product2 = platform.newProduct("test2");
		
		Bid bid2 = seller.createBid(product, limit).
					setReservePrice(seller, reservePrice).
					publishIt(seller);
		
		bid.unregisterAlertListener(fakeUser1, AlertType.HIGHER_OFFER_DONE); //We unregister fakeUser1 from the first bid.
		
		bid2.registerAlertListener(fakeUser1, AlertType.BID_CANCELLED);//We register fakeUser1 for the alert BID_CANCELLED
		
		bid2.cancelIt(seller);//We cancel bid2, fakeUser1 should receive an Alert.
		
		assertThat(fakeUser1.receivedBid, is(bid2));
		assertTrue(fakeUser1.receivedBid.isCancelled());
	}
	
}

class FakeUser implements User {

	private final OfferFactory offerFactory;
	
	public Bid receivedBid;
	public float newOfferPrice;
	
	FakeUser(OfferFactory offerFactory) {
		this.offerFactory = offerFactory;
	}
	
	@Override
	public void reservePriceReached(Bid b) {
		AlertSpecifications.asLogger.info("Hello from FakeUser.reservePriceReached() !");
		
		this.receivedBid = b;
	}

	@Override
	public void bidCancelled(Bid b) {
		AlertSpecifications.asLogger.info("Hello from FakeUser.bidCancelled() !");
		
		this.receivedBid = b;
	}

	@Override
	public void higherOfferAdded(Bid b, float newOfferPrice) {
		AlertSpecifications.asLogger.info("Hello from FakeUser.higherOfferAdded() !");
		
		this.newOfferPrice = newOfferPrice;
		this.receivedBid = b;
	}

	@Override
	public void bid(Bid bid, float price) {
		bid.addOffer(
			this.offerFactory.createNewOffer(bid, this, price)
		);
	}

	@Override
	public void subscribeToAlerts(Bid bid, AlertType... alerts) {}

	@Override
	public Bid createBid(Product p, Date limit) { return null; }

	@Override
	public Bid createBid(Product p, Date limit, float minPrice) { return null; }

	@Override
	public void publishBid(Bid bid) {}

	@Override
	public void receivedNewOffer(Bid bid, Offer newOffer) { }

	@Override
	public String getFirstName() { return null; }

	@Override
	public String getLastName() { return null; }

	@Override
	public String getLogin() { return null; }
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

