package fr.esiea.ooa.ebaylike.api;

import java.util.Collection;
import java.util.Date;
import java.util.List;


public abstract class AbstractBid implements Bid {

	private Date limitDate;
	private float minPrice;
	private float reservPrice;
	private Enum bidState;
	private List<Offer> offers;
	private Seller seller;
	private Product product;


	@Override
	public Bid setMinimumPrice(float price) {
		this.minPrice = price;
		return this;
	}

	@Override
	public Bid setReservePrice(float price) {
		this.reservPrice = price;
		return this;
	}

	@Override
	public float getMinPrice() {
		return this.minPrice;

	}

	@Override
	public Enum getBidState(){
		return this.bidState;
	}

	@Override
	public Date getLimitDate() {
		return this.limitDate;
	}


	@Override
	public Collection<Offer> getBidOffer() {
		return this.offers;
	}

	@Override
	public Product getProduct() {
		return this.product;
	}
}
