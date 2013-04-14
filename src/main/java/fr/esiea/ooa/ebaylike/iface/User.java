package fr.esiea.ooa.ebaylike.iface;


public interface User  extends Buyer,Seller{

	public String getFirstName();
	public String getLastName();
	public String getLogin();
}
