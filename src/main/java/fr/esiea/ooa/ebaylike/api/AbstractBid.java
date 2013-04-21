package fr.esiea.ooa.ebaylike.api;

import java.util.Date;
import java.util.List;


public abstract class AbstractBid implements Bid {

 		private Date limitDate;
 		private float minPrice;
 		private float reservPrice;
 		private Enum bidState;
 		private List<Offer> offers;
 		private Seller seller;
 		private Product product;
 		
 		
 		public void fireOfferone(){
 			
 			
 		}
}
