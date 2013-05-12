package fr.esiea.ooa.ebaylike.default_impl;

import fr.esiea.ooa.ebaylike.api.Alert;
import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.factory.AlertFactory;

public class DefaultAlertFactory implements AlertFactory {

	@Override
	public Alert createNewAlert(User u, Bid bid, AlertType alert) {
		// TODO Auto-generated method stub
		return null;
	}

}
