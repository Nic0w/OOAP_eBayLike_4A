/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.persistence;

/**
 * @author nic0w
 *
 */
public interface Persistor<O, F> {

	public F getFactory();
	
	public O get(Object id);
	
	public void store(O o) throws StorageException;
	
	public void remove(O o);
	
}
