package fr.esiea.ooa.ebaylike.iface.listener;

import fr.esiea.ooa.ebaylike.core.Offer;
import fr.esiea.ooa.ebaylike.iface.Seller;

public interface SellerBidListener extends Seller {

	public void offerDone(Offer offer);
}
