/**
 * 
 */
package fr.esiea.ooa.ebaylike.api.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nic0w
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD})
public @interface Index {

}
