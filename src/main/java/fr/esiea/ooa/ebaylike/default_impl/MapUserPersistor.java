/**
 * 
 */
package fr.esiea.ooa.ebaylike.default_impl;

import java.util.HashMap;
import java.util.Map;

import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.factory.UserFactory;
import fr.esiea.ooa.ebaylike.api.persistence.Persistor;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;

/**
 * @author nic0w
 *
 */
public class MapUserPersistor implements Persistor<User, UserFactory> {

	private final UserFactory userFactory;
	
	private final Map<String, User> users;
	
	/**
	 * 
	 */
	MapUserPersistor(UserFactory factory) {
		this.userFactory = factory;
		
		this.users = new HashMap<>();
	}

	@Override
	public UserFactory getFactory() {
		return this.userFactory;
	}

	@Override
	public User get(Object key) {
		return this.users.get(key);
	}

	@Override
	public void store(User o) throws StorageException {
		try {
			this.users.put(o.getLogin(), o);
		}
		catch(Exception e) {
			throw new StorageException(e);
		}
	}

	@Override
	public void remove(User o) {
		this.users.remove(o.getLogin());
	}

}
