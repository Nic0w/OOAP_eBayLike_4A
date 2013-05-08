package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.event.AlertType;



public interface User extends Buyer, Seller {

	/**
	 * @return
	 */
	public String getFirstName();
	
	/**
	 * @return
	 */
	public String getLastName();	
	
	/**
	 * @return
	 */
	public String getLogin();
	
	
}
