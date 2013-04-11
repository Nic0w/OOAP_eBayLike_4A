package fr.esiea.ooa.ebaylike.abstra;

import java.util.Date;
import java.util.List;

import fr.esiea.ooa.ebaylike.Seller;
import fr.esiea.ooa.ebaylike.interfac.Bid;
import fr.esiea.ooa.ebaylike.interfac.Offer;
import fr.esiea.ooa.ebaylike.interfac.Product;

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
