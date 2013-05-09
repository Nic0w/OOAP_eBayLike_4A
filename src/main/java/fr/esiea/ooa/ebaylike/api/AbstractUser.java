package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.persistence.Persistor;

/**
 * @author Nicolas Remi Romain
 *
 */
public abstract class AbstractUser implements User {

	private final String login;
	private final String firstName;
	private final String lastName;
	
	protected AbstractUser(String login, String firstname, String lastname) {
		
		this.login = login;
		this.firstName = firstname;
		this.lastName = lastname;
	}
	
	@Override
	public final String getFirstName() {
		return this.firstName;
	}
	@Override
	public final String getLastName() {
		return this.lastName;
	}
	@Override
	public final String getLogin() {
		return this.login;
	}
	
	@Override
	public final void subscribeToAlerts(Bid bid, AlertType... alerts) {
		bid.registerAlertListener(this, alerts);
	}

	@Override
	public final Bid publishBid(Bid bid) {
		return bid.publishIt(this);
	}
}
