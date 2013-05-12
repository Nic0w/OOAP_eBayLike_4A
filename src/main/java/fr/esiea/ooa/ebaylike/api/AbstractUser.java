package fr.esiea.ooa.ebaylike.api;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.factory.BidFactory;
import fr.esiea.ooa.ebaylike.api.factory.OfferFactory;
import fr.esiea.ooa.ebaylike.api.persistence.Index;

/**
 * @author Nicolas Remi Romain
 *
 */
public abstract class AbstractUser implements User {

	
	@Index private final String login;
	
	private final String firstName;
	private final String lastName;
	
	private final BidFactory bidFactory;
	private final OfferFactory offerFactory;
	
	protected AbstractUser(BidFactory bidFactory, OfferFactory offerFactory, String login, String firstname, String lastname) {
		
		this.login = login;
		this.firstName = firstname;
		this.lastName = lastname;
		
		this.bidFactory = bidFactory;
		this.offerFactory = offerFactory;
	}
	
	@Override
	public Bid createBid(Product p, Date limit, float minPrice) {
		
		if(limit == null) throw new NullPointerException("Limit Date cannot be null !");
		
		return this.bidFactory.createBid(this, p, limit, minPrice);
	}
	
	@Override
	public final void bid(Bid bid, float price) {
		
		Offer o = this.offerFactory.createNewOffer(bid, this, price);
		
		bid.addOffer(o);
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
	public final void publishBid(Bid bid) {
		bid.publishIt(this);
	}
}
