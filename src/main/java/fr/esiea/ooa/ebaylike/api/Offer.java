package fr.esiea.ooa.ebaylike.api;

/**
 * @author girardremi
 *
 */
public interface Offer {

	/**
	 * @return the price of an offer
	 */
	public float getPrice();
		
	public Buyer getEmitter();
	
}
