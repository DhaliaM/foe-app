/**
 * @(#)GalaxieChance.java
 *
 */
package drunk.homebrew.forge.of.empires.app.service;

import java.util.Random;

/**
 * Diese Klasse überprüft nur ob eine Wahrscheinlichkeit eingetreten ist
 * @param d - Chance in Double, als Bsp. übergeben 0.3 entspricht 30%
 * @return true or false
 * @author Dhalia
 *
 */

public class GalaxieChance {
	
	public boolean chance(double d) {
		Random rng = new Random();
		Integer zufallszahl = rng.nextInt(101);
		if(zufallszahl < d*100) {
			return true;
		}
		else
		{
		return false;
		}
	}
}
