//Q: Was sind das für Kommentare?
/**
 * @(#)GalaxieChance.java
 *
 */
package drunk.homebrew.forge.of.empires.app.service;

import java.util.Random;

/**
 * Diese Klasse überprüft nur ob eine Wahrscheinlichkeit eingetreten ist
 * FIXME: Ab hier gehört es an die Javadoc der Methode
 * @param d - Chance in Double, als Bsp. übergeben 0.3 entspricht 30%
 * @return true or false
 * FIXME: bis hier hin
 * @author Dhalia
 * FIXME: Unnötige Leerzeilen vermeiden
 *
 */
//FIXME: Unnötige Leerzeilen vermeiden

//FIXME: Wenn die Klasse nur von "Looting" verwendet werden soll, kann die Klasse auch "package-privat" sein
public class GalaxieChance {

	//FIXME: Javadoc fehlt
	//FIXME: Variablenname "d" ist nicht sehr sprechend
	//FIXME: Bei boolean Rückgabewert einer Methoden, sollte der Methodenname wie eine Frage fomruliert sein. Beispiel: "hasGalaxyChance"
	public boolean chance(double d) {
		//FIXME: Variablenname "rng" ist nicht sehr sprechend
		Random rng = new Random();
		//FIXME: Denglisch vermeiden
		//FIXME: Mit primitiven Typen arbeiten, da auch ein primitiver Typ zurückgegeben wird
		Integer zufallszahl = rng.nextInt(101);
		//FIXME: Anstatt mit if-else zu arbeiten einfach "return zufallszahl < d*100"
		if(zufallszahl < d*100) {
			return true;
		}
		else
		{
		return false;
		}
	}
}
