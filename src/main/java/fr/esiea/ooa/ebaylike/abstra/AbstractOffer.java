package fr.esiea.ooa.ebaylike.abstra;

import fr.esiea.ooa.ebaylike.Buyer;
import fr.esiea.ooa.ebaylike.interfac.Offer;

public abstract class AbstractOffer implements Offer {

	public float price;
	public Buyer emitter;
}
