package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.exception.BadSellerException;


/**
 * 
 * @author Nicolas Remi Romain
 * 
 *
 */

public interface Bid {

		/**
		 * 
		 * @return
		 * 
		 */
		public Bid publishIt(Seller s);
		
		/**
		 * @param seller
		 * @return
		 * @throws BadSellerException
		 */
		public Bid cancelIt(Seller seller) throws BadSellerException;
	
		/**
		 * @param s
		 * @param price
		 * @return
		 * @throws BadSellerException 
		 */
		public Bid setMinimumPrice(Seller s, float price) throws BadSellerException;
		
		/**
		 * @param price
		 * @return
		 * @throws BadSellerException 
		 */
		public Bid setReservePrice(Seller s, float price) throws BadSellerException;
		
		/**
		 * @param s
		 * @return
		 * @throws BadSellerException 
		 */
		public float getReservePrice(Seller s) throws BadSellerException;
		
		/**
		 * @param user
		 * @param alerts
		 * @return
		 */
		public Bid registerAlertListener(User user, AlertType... alerts);
		
		/**
		 * @param user
		 * @param alerts
		 * @return
		 */
		public Bid unregisterAlertListener(User user, AlertType... alerts);
		
		/**
		 * add an offer to an Bid
		 * 
		 * @param o the offer to add
		 */
		public void addOffer(Offer o);
		
		/**
		 * 
		 * return the state of a Bid : is it finished or not?
		 * 
		 * @return true or false
		 */
		public boolean isFinished();
		
		/**
		 * 
		 * return the state of a Bid : is it published or not?
		 * 
		 * @return true or false
		 */
		public boolean isPublished();
		
		/**
		 * 
		 * return the state of a Bid : is it cancelled or not?
		 * 
		 * @return true or false
		 */
		public boolean isCancelled();
		
		/**
		 * 
		 * return true if the Reserve Price of the Bid has benn reached
		 * return false if not
		 * 
		 * @return true or false
		 */
		public boolean hasReservePriceBeenReached();

		/**
		 * 
		 * this method return the product of the Bid
		 * 
		 * @return the Product of a Bid
		 */
		public Product getProduct();
		
		/**
		 * 
		 * 
		 * the method return the limit Date of a Bid 
		 * after this date nobody can makes offer on  bid
		 * 
		 * @return the limit Date of a Bid
		 */
		public Date getLimitDate();
				
		/**
		 * 
		 * the method return the minimum price of a Bid
		 * ( this minimum price has been set by the seller )
		 * 
		 * @return the Minimum price of a Bid
		 */
		public float getMinPrice();
		
		/**
		 * 
		 * this method return the last offer price
		 * 
		 * @return the last offer Price
		 */
		public float getLastOfferPrice();
}
