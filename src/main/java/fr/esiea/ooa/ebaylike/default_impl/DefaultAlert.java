package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.Alert;
import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.event.AlertType;

public class DefaultAlert implements Alert {

	private final User user;
	private final Bid bid;
	private final AlertType alert;
	
	public DefaultAlert(User u, Bid bid, AlertType alert) {
		this.user = u;
		this.bid = bid;
		this.alert = alert;
	}
	
	@Override
	public User getUser() {
		return this.user;
	}

	@Override
	public AlertType getAlertType() {
		return this.alert;
	}

	@Override
	public Bid getBid() {
		return this.bid;
	}

}
