package fr.esiea.ooa.ebaylike.api.event;

import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.default_impl.DefaultOffer;

public interface SellerBidListener {

	public void bidStateChanged(BidAlert alert);
}
