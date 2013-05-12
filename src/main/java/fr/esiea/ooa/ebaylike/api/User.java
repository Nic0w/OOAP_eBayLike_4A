package fr.esiea.ooa.ebaylike.api;




/**
 * @author Nicolas Remi Romain
 *
 */

public interface User extends Buyer, Seller {

	/**
	 * 
	 * this method return the First name of an user
	 * 
	 * @return the first name of an User
	 */
	public String getFirstName();
	
	/**
	 * 
	 * this method return the Last name of an user
	 * 
	 * @return the last name of an user
	 */
	public String getLastName();	
	
	/**
	 * 
	 * this method return the login of an user
	 * 
	 * @return the login of an user
	 */
	public String getLogin();
	
	
}
