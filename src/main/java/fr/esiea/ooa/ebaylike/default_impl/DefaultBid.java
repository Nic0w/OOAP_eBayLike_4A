package fr.esiea.ooa.ebaylike.default_impl;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.AbstractBid;
import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Offer;
import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.event.AlertType;
import fr.esiea.ooa.ebaylike.api.exception.BadSellerException;

public class DefaultBid extends AbstractBid{



	@Override
	public Bid publishIt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bid cancelIt(Seller seller) throws BadSellerException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Bid registerAlertListener(User user, AlertType... alerts) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addOffer(Offer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void isFinished() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testReservePrice() {
		// TODO Auto-generated method stub

	}


}
