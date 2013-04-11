package fr.esiea.ooa.ebaylike.core;

import java.util.Date;
import java.util.List;

import fr.esiea.ooa.ebaylike.iface.Bid;
import fr.esiea.ooa.ebaylike.iface.Offer;
import fr.esiea.ooa.ebaylike.iface.Product;
import fr.esiea.ooa.ebaylike.iface.Seller;

public abstract class AbstractBid implements Bid {

 		private Date limitDate;
 		private float minPrice;
 		private float reservPrice;
 		private Enum bidState;
 		private List<Offer> offers;
 		private Seller seller;
 		private Product product;
 		
 		public AbstractBid(){
 			
 		}
 		
 		
 		public void fireOfferDone(){
 			
 		}
}
