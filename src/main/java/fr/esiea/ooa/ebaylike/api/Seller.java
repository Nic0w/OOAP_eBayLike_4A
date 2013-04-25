package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.event.SellerBidListener;


public interface Seller extends SellerBidListener {

	public Bid createBid(Product p, Date limit);
	
	public Bid createBid(Product p, Date limit, float minPrice);
		
	public void publishBid(Bid bid);

}
