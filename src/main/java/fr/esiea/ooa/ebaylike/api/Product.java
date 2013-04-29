package fr.esiea.ooa.ebaylike.api;

public interface Product {

	public String getID();	
	public String getDescription();	
	public Product setDescription(String text);
	public Product setID(String identifiant);
	
}
