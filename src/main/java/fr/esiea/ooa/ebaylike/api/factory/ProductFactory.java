/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.factory;

import fr.esiea.ooa.ebaylike.api.Product;

/**
 * @author nic0w
 *
 */
public interface ProductFactory {

	public Product createNewProduct(String description);
	
}
