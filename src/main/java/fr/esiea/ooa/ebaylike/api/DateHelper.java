/**
 * 
 */
package fr.esiea.ooa.ebaylike.api;

import java.util.Calendar;
import java.util.Date;

/**
 * @author nic0w
 *
 */
public class DateHelper {

	private static Calendar calendar = Calendar.getInstance();
	
	public static Date getDateFor(int day, int month, int year) {
		
		calendar.set(year, month, day);
		
		return calendar.getTime();
	}
	
	public static Date getTomorrowSameHour() {
		
		calendar.setTime(new Date());
		
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		
		return calendar.getTime();
	}
	
}
