/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.Set;

import fr.esiea.ooa.ebaylike.api.event.AlertType;

/**
 * @author nic0w
 *
 */
public interface AlertManager {

	public final boolean REGISTER   = true;
	public final boolean UNREGISTER = !REGISTER;
	
	public void manageUserAlerts(Bid bid, User u, boolean state, AlertType... alerts);

	public Set<User> getRegisteredUsers(AlertType... alerts);

}
