package fr.esiea.ooa.ebaylike.default_impl.factory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.esiea.ooa.ebaylike.api.Product;
import fr.esiea.ooa.ebaylike.api.factory.ProductFactory;
import fr.esiea.ooa.ebaylike.default_impl.DefaultProduct;

public class DefaultProductFactory implements ProductFactory {
	
	
	/**
	 * Converts an array of bytes to a String containing its hexadecimal representation.
	 * 
	 * Code from : http://rgagnon.com/javadetails/java-0596.html
	 * 
	 * @param b An array of bytes.
	 * @return An hexadecimal representation of the submitted array.
	 */
	private static String byteArrayToHexString(byte[] b) {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
		}
	
	private static String uniqueID(String description) throws NoSuchAlgorithmException {
			
		return byteArrayToHexString(
				MessageDigest.getInstance("SHA-1").
					digest(
						description.getBytes()
					)
				);
	}
	
	@Override
	public Product createNewProduct(String description) {
		
		String uniqueID = null;
		
		try {
			uniqueID = uniqueID(description);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		if(uniqueID == null) 
			return null;
		else
			return new DefaultProduct(uniqueID, description);
	}
}
