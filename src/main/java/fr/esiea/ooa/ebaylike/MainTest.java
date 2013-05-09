package fr.esiea.ooa.ebaylike;

import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.default_impl.JavaCollectionsPersistenceAgent;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();
		
		storageAgent.store("noob", "Nic0w");
		storageAgent.store("1235", "Bouyaka");
		storageAgent.store(3, "Thuma");
		storageAgent.store("noob", 5.3);
		
		System.out.println(storageAgent.get(String.class, "noob"));
		System.out.println(storageAgent.get(Double.class, "noob"));
		System.out.println(storageAgent.get(String.class, 3));
		
		for(Double s : storageAgent.getAll(Double.class)) {
			System.out.println(s);
		}
		
		
		/*BidPlatform bidPlatform = new BidPlatformBuilder(storageAgent).build();
		
		try {
			User u = bidPlatform.newUser("noob", "Noob", "Noob");
			
			System.out.println(u);
			
			Bid b = u.createBid(null, null).setMinimumPrice(0).setReservePrice(0).publishIt();
			
			
			
			u.bid(b, 0);
			
		} catch (UserAlreadyExistsException | StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
