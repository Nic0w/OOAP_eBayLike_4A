package fr.esiea.ooa.ebaylike.api;

import java.util.Collection;
import java.util.Date;

import fr.esiea.ooa.ebaylike.api.event.AlertType;


public interface Bid {

		public Date getLimitDate();
		public Enum getBidState();
		public float getMinPrice();
		public void setBidState();
		public Collection<Offer> getBidOffer();
		public void addBidOffer();
		public void isFinished();
		public void allertSeller();		
		public void cancelBid(Seller seller);		
		public void testReservePrice();
		public Product getProduct();
		public void registerAlertListener(AbstractUser abstractUser,
				AlertType[] alerts);
		public Object setMinimumPrice(int i);
		public Bid setMinimumPrice(float price);
		Bid setReservePrice(float price);
}
