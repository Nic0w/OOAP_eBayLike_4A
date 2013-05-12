package fr.esiea.ooa.ebaylike.api;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.esiea.ooa.ebaylike.api.exception.BadSellerException;
import fr.esiea.ooa.ebaylike.api.exception.IllegalActionException;


/**
 * @author Nicolas Remi Romain
 *
 */
public abstract class AbstractBid implements Bid {

	private static final Logger abLogger = LoggerFactory.getLogger(AbstractBid.class);
	
	private final Date limitDate;
	private final Seller seller;
	private final Product product;
	
	protected BidState bidState;
	private float minPrice;
	private float reservePrice;

	
	protected AbstractBid(Seller seller, Product product, Date limit) {
		
		this.seller = seller;
		this.product = product;
		this.limitDate = limit;
		
		this.bidState = BidState.CREATED;
	}
	
	/**
	 * 
	 * test if the seller who wants to change properties of a Bid
	 * is the one who created it
	 * 
	 * @param s the Seller
	 * @return true
	 * @throws BadSellerException
	 */
	private final boolean sellerIsOK(Seller s) throws BadSellerException {
		
		if(!this.seller.equals(s)) 
			throw new BadSellerException(String.format("Seller %s is not the one who created this bid.", s));
		else 
			return true;
	}
	
	/**
	 * 
	 * test is the price is >0
	 * 
	 * @param price
	 * @return true
	 */
	private final boolean priceIsOK(float price) {
		
		if(price < 0.0f) 
			throw new IllegalArgumentException(String.format("Price value cannot be negative : price=%f.", price));
		else
			return true;
	}
	
	protected final Seller getSeller() {
		return this.seller;
	}
	
	protected final float getReservePrice() {
		return this.reservePrice;
	}
	
	@Override
	public final Bid setMinimumPrice(Seller s, float price) throws BadSellerException {
		
		if(sellerIsOK(s) && priceIsOK(price)) this.minPrice = price;
		
		return this;
	}

	@Override
	public final Bid setReservePrice(Seller s, float price) throws BadSellerException {
		
		if(sellerIsOK(s) && priceIsOK(price)) this.reservePrice = price;
		
		return this;
	}

	@Override
	public final Bid publishIt(Seller s) throws BadSellerException {
		
		if(sellerIsOK(s)) this.bidState = BidState.PUBLISHED;
		
		return this;
	}

	@Override
	public final Bid cancelIt(Seller seller) throws BadSellerException {
		
		if(this.hasReservePriceBeenReached())
			throw new IllegalActionException("Cannot cancel a bid when the reserve price has been reached !");
		
		if(sellerIsOK(seller)) this.bidState = BidState.CANCELLED;
		
		this.fireBidCancelled();
		
		return this;
	}

	@Override
	public float getReservePrice(Seller s) throws BadSellerException {
		
		if(sellerIsOK(s)) return this.reservePrice;
		
		return -1; //should not happen
	}

	@Override
	public final boolean isFinished() {
		
		Calendar currentTime = Calendar.getInstance();
		
		Calendar limiteDate = Calendar.getInstance();
		limiteDate.setTime(this.limitDate);
		
		if(currentTime.compareTo(limiteDate) >= 0) 
			this.bidState = BidState.FINISHED;
		
		return this.bidState == BidState.FINISHED;
	}

	@Override
	public final boolean isPublished() {
		return this.bidState == BidState.PUBLISHED;
	}

	@Override
	public final boolean isCancelled() {
		return this.bidState == BidState.CANCELLED;
	}

	@Override
	public final boolean hasReservePriceBeenReached() {
		return this.getLastOfferPrice() >= this.reservePrice;
	}

	@Override
	public final float getMinPrice() {
		return this.minPrice;
	}
	
	@Override
	public final Date getLimitDate() {
		return this.limitDate;
	}

	@Override
	public final Product getProduct() {
		return this.product;
	}
	
	@Override
	public void addOffer(Offer o) {
		
		abLogger.debug("This offer price is : {}.", o.getPrice() );
		
		if(o.getEmitter().equals(this.seller))
			throw new IllegalActionException("The seller can't make an offer on its own bid.");
		
		if(!this.isPublished() || this.isCancelled() || this.isFinished())
			throw new IllegalActionException("You cannot make an offer on this Bid !");
		
		if(o.getPrice() < this.minPrice)
			throw new IllegalActionException("You must make an offer higher than the minimum price.");
		
		
		abLogger.debug("Last offer price is {}.", this.getLastOfferPrice());
		
		if(o.getPrice() <= this.getLastOfferPrice())
			throw new IllegalActionException("You must make an offer higher than the last offer.");
		
		this.seller.receivedNewOffer(this, o);
		
		fireHigherOfferAdded(o);
		
	}
	
	protected abstract void fireReservePriceReached();
	
	protected abstract void fireBidCancelled();
	
	protected abstract void fireHigherOfferAdded(Offer o);
}
