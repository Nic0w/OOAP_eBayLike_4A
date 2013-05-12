/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.persistence;

import java.util.List;

/**
 * @author nic0w
 *
 */
public interface FilteredTable<T> {

	public Table<T> isEqualTo(Object o);
	
}
