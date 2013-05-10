package fr.esiea.ooa.ebaylike.default_impl;

import java.util.Date;

import fr.esiea.ooa.ebaylike.api.PersistentBid;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.Seller;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;

/**
 * @author Nicolas Remi Romain
 *
 */
public class DefaultBid extends PersistentBid {

	public DefaultBid(PersistenceAgent storage, Seller seller, Product product, Date limit) {
		super(storage, seller, product, limit);
		
	}
}
