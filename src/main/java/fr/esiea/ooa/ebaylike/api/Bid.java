package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.exception.BadSellerException;
import fr.esiea.ooa.ebaylike.api.exception.IllegalActionException;


public interface Bid {

		/**
		 * @return
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
		 * @throws IllegalActionException 
		 */
		public Bid setMinimumPrice(Seller s, float price) throws BadSellerException;
		
		/**
		 * @param price
		 * @return
		 * @throws IllegalActionException 
		 */
		public Bid setReservePrice(Seller s, float price) throws BadSellerException;
		
		/**
		 * @param user
		 * @param alerts
		 * @return
		 */
		public Bid registerAlertListener(User user, AlertType... alerts);
		
		public void addOffer(Offer o);
		
		public boolean isFinished();
		
		public boolean isPublished();
		
		public boolean isCancelled();
		
		public boolean hasReservePriceBeenReached();

		public Product getProduct();
		
		public Date getLimitDate();
				
		public float getMinPrice();
		
		public float getLastOfferPrice();
}
