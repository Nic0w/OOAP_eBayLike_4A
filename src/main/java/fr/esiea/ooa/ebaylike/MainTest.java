package fr.esiea.ooa.ebaylike;

import fr.esiea.ooa.ebaylike.api.Bid;
import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.User;
import fr.esiea.ooa.ebaylike.api.exception.UserAlreadyExistsException;
import fr.esiea.ooa.ebaylike.api.persistence.StorageException;

public class MainTest {

	/**
	 * 
	 * main test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		BidPlatform platform = BidPlatform.getDefaultInstance();

		try {
			
			User noob = platform.newUser("noob", "Benjamin", "Franklin");
			
			User noob2 = platform.newUser("noob2", "Benjamin", "Franklin");
			
			Product car = platform.newProduct("A red car.");
			
			Bid b = noob.createBid(car, null);
			
			noob2.bid(b, 5);
			
			
			/*CollectionsPA2 storage = (CollectionsPA2) platform.getPersistenceAgent();
			
			for (User u : storage.get(User.class).where("login").isEqualTo("noob")) {
				
				System.out.println(u.getLogin());
				
			}*/
			
		} catch (UserAlreadyExistsException | StorageException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}

}
