package fr.esiea.ooa.ebaylike.iface;


public interface User  extends Buyer,Seller{

	public void getFirstName();
	public void getLastName();
	public void getLogin();
}
