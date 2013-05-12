package fr.esiea.ooa.ebaylike.api;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.factory.AlertFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent2;

public class PersistentAlertManager implements AlertManager {
	
	private final PersistenceAgent2 storage;
	
	private final AlertFactory alertFactory;
	
	public PersistentAlertManager(PersistenceAgent2 storage, AlertFactory alertFactory) {
		this.storage = storage;
		this.alertFactory = alertFactory;
	}

	@Override
	public void manageUserAlerts(Bid bid, User user, boolean registration, AlertType... alerts) {
		
		for(AlertType alert : alerts) {
			
			if(registration) {
				
				Alert newAlert = this.alertFactory.createNewAlert(user, bid, alert);
				
				this.storage.store(newAlert);
				
			} 
			else {
				this.storage.get(Alert.class).
					where(Bid.class).isEqualTo(bid).
					where(User.class).isEqualTo(user).
					where(AlertType.class).isEqualTo(alert).
						removeAll();
			}
		}
	}

	@Override
	public Set<User> getRegisteredUsers(AlertType... alerts) {
		
		Set<User> registeredUsers = new HashSet<>();
		
		for(AlertType alert : alerts)
			for(Alert registeredAlerts : this.storage.get(Alert.class).where(AlertType.class).isEqualTo(alert))
				registeredUsers.add(registeredAlerts.getUser());
		
		return registeredUsers;
	}

}