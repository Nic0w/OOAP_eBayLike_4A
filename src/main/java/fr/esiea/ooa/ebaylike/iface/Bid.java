package fr.esiea.ooa.ebaylike.iface;


public interface Bid {

		public void getLimitDate();
		public void getBidState();
		public void getMinPrice();
		public void setBidState();
		public void getBidOffer();
		public void addBidOffer();
		public void isFinished();
		public void allertSeller();
		public void cancelBid(Seller seller);
		
		public void testReservePrice();
		public void getProduct();
}
