package fr.esiea.ooa.ebaylike;

import java.util.ArrayList;
import java.util.Collection;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.persistence.PersistenceAgent;
import fr.esiea.ooa.ebaylike.default_impl.JavaCollectionsPersistenceAgent;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PersistenceAgent storageAgent = new JavaCollectionsPersistenceAgent();
		
		storageAgent.store("noob", new ArrayList<String>());
		
		
		ArrayList<String> strings = storageAgent.get(ArrayList.class, "noob");
		
		strings.add("OMG1");
		strings.add("OMG2");
		
		ArrayList<String> strings2 = storageAgent.get(ArrayList.class, "noob");
		
		for(String s : strings2) {
			System.out.println(s);
		}
		
		
		/*BidPlatform bidPlatform = BidPlatform.getDefaultInstance();
		
		try {
			User u = bidPlatform.newUser("noob", "Noob", "Noob");
			
			System.out.println(u);
			
			Bid b = u.createBid(null, null);
			
			
			
			u.bid(b, 0);
			
		} catch (UserAlreadyExistsException | StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
