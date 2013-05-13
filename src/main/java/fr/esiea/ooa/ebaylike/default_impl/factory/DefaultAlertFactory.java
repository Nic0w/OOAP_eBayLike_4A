package fr.esiea.ooa.ebaylike.default_impl.factory;

import fr.esiea.ooa.ebaylike.api.Alert;
import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.factory.AlertFactory;
import fr.esiea.ooa.ebaylike.default_impl.DefaultAlert;

public class DefaultAlertFactory implements AlertFactory {

	@Override
	public Alert createNewAlert(User u, Bid bid, AlertType alert) {
		return new DefaultAlert(u, bid, alert);
	}

}
