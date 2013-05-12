/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.factory;

import fr.esiea.ooa.ebaylike.api.Alert;
import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.event.AlertType;

/**
 * @author nic0w
 *
 */
public interface AlertFactory {

	public Alert createNewAlert(User u, Bid bid, AlertType alert);

}
