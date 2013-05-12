/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.Date;
import java.util.List;
import java.util.Set;

import fr.esiea.ooa.ebaylike.api.event.AlertType;

/**
 * @author Nicolas Remi Romain
 *
 */
public class PersistentBid extends AbstractBid {

	private final OfferManager offerManager;

	private final AlertManager alertManager;
	
	
	/**
	 * @param seller
	 * @param product
	 * @param limit
	 */
	public PersistentBid(PersistentOfferManager offerMng, PersistentAlertManager alertMng, Seller seller, Product product, Date limit) {
		super(seller, product, limit);
	
		this.offerManager = offerMng;
		this.alertManager = alertMng;
	}


	@Override
	public void addOffer(Offer o) {
		
		this.offerManager.storeOffer(o);
		
		super.addOffer(o);
	}

	@Override
	public final float getLastOfferPrice() {

		return this.offerManager.getLastOffer().getPrice();
	}

	
	@Override
	public final Bid registerAlertListener(User user, AlertType... alerts) {
		
		this.alertManager.manageUserAlerts(this, user, AlertManager.REGISTER, alerts);
		
		return this;
	}

	@Override
	public final Bid unregisterAlertListener(User user, AlertType... alerts) {
		
		this.alertManager.manageUserAlerts(this, user, AlertManager.UNREGISTER, alerts);
		
		return this;
	}


	@Override
	protected final void fireReservePriceReached() {
		
		for(User u : this.alertManager.getRegisteredUsers(AlertType.RESERVE_PRICE_REACHED)) 
			u.reservePriceReached(this);
	}


	@Override
	protected final void fireBidCancelled() {
		
		for(User u : this.alertManager.getRegisteredUsers(AlertType.BID_CANCELLED)) 
			u.bidCancelled(this);
	}


	@Override
	protected final void fireHigherOfferAdded(Offer o) {
		
		Set<User> registeredUsers = this.alertManager.getRegisteredUsers(AlertType.HIGHER_OFFER_DONE);
		
		registeredUsers.remove(o.getEmitter());
		
		for(User u : registeredUsers) 
			u.higherOfferAdded(this, o.getPrice());
		
	}
}
