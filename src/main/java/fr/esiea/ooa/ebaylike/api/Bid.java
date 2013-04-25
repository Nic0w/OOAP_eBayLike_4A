package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.exception.BadSellerException;


public interface Bid {

		/**
		 * @return
		 */
		public Bid publishIt();
		
		/**
		 * @param seller
		 * @return
		 * @throws BadSellerException
		 */
		public Bid cancelIt(Seller seller) throws BadSellerException;
	
		/**
		 * @param price
		 * @return
		 */
		public Bid setMinimumPrice(float price);
		
		/**
		 * @param price
		 * @return
		 */
		public Bid setReservePrice(float price);
		
		/**
		 * @param user
		 * @param alerts
		 * @return
		 */
		public Bid registerAlertListener(User user, AlertType... alerts);
		
		
		public void getLimitDate();
		
		public void getBidState();
		
		public void getMinPrice();
		
		public void getBidOffer();
		
		public void addOffer(Offer o);
		
		public void isFinished();
		
		public void testReservePrice();

		public void getProduct();
		
		
		
		
}
