/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import fr.esiea.ooa.ebaylike.api.event.AlertType;

/**
 * @author nic0w
 *
 */
public interface Alert {

   public User getUser();

	public AlertType getAlertType();
	
	
	
}
