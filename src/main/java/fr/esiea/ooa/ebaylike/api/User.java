package fr.esiea.ooa.ebaylike.api;



public interface User  extends Buyer,Seller{

	public String getFirstName();
	
	public String getLastName();
	
	public String getLogin();
	
}
