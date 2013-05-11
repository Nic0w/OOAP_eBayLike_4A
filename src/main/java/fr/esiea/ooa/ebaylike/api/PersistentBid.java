/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.LinkedList;

import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author Nicolas Remi Romain
 *
 */
public abstract class PersistentBid extends AbstractBid {

	private final PersistenceAgent storage;
	
	/**
	 * @param seller
	 * @param product
	 * @param limit
	 */
	public PersistentBid(PersistenceAgent storage, Seller seller, Product product, Date limit) {
		super(seller, product, limit);
	
		this.storage = storage;
		
		if(storage.get(LinkedList.class, product.getID()) == null)
			storage.store(product.getID(), new LinkedList<Offer>());
	}


	@Override
	public void addOffer(Offer o) {
		
		LinkedList<Offer> offers = this.storage.get(LinkedList.class, this.getProduct().getID());
		
		offers.add(o);
		
		super.addOffer(o);
	}

	@Override
	public final float getLastOfferPrice() {
		
		LinkedList<Offer> offers = this.storage.get(LinkedList.class, this.getProduct().getID());
		
		return offers.getLast().getPrice();
	}


	private Bid alertsRegistration(boolean state, User u, AlertType... alerts) {
		
		EnumMap<AlertType, Boolean> registeredAlerts = this.storage.get(EnumMap.class, super.getSeller());
		
		if(registeredAlerts == null) {
			registeredAlerts = new EnumMap<>(AlertType.class);
			this.storage.store(super.getSeller(), registeredAlerts);
		}
		
		for(AlertType type : alerts)
			registeredAlerts.put(type, state);
		
		return this;
	}
	
	@Override
	public final Bid registerAlertListener(User user, AlertType... alerts) {
		return this.alertsRegistration(true, user, alerts);
	}

	@Override
	public final Bid unregisterAlertListener(User user, AlertType... alerts) {
		return this.alertsRegistration(false, user, alerts);
	}


	@Override
	protected final void fireReservePriceReached() {
		
		
	}


	@Override
	protected final void fireBidCancelled() {
		EnumMap<AlertType, Boolean> registeredAlerts = this.storage.get(EnumMap.class, super.getSeller());
		
		if(registeredAlerts == null) return;
		
		for()
		
	}


	@Override
	protected final void fireHigherOffer() {
		
	}
	
	
}
