package fr.esiea.ooa.ebaylike.api;

/**
 * @author Nicolas Remi Romain
 */
public interface Product {

	/**
	 * 
	 * return the ID af a product
	 * 
	 * @return ID
	 */
	public String getID();	
	
	/**
	 * 
	 * return the Description of a product
	 * 
	 * @return the description
	 */
	public String getDescription();	
	
	/**
	 * 
	 * to set the description of a product
	 * 
	 * @param text
	 * @return the Product
	 */
	public void setDescription(String text);
	
}
