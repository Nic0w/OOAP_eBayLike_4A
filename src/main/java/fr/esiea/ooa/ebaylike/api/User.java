package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.event.AlertType;



public interface User extends Buyer, Seller {

	public String getFirstName();
	
	public String getLastName();
	
	public String getLogin();
	
	
}
