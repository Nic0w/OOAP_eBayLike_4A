package fr.esiea.ooa.ebaylike;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BidSpecifications.class, 
		PlatformSpecifications.class,
		UserSpecifications.class })
public class AllTests {

}
