package coffee.homebrew.forge.of.empires.app.service;

import java.util.Random;

/**
 * Diese Klasse überprüft nur ob eine Wahrscheinlichkeit eingetreten ist.
 *
 * @author Dhalia
 */

class GalaxyChance {
    /**
     * Die Methode ermittelt eine Zufallszahl zwischen 0 und 100 und prüft ob diese Zahl kleiner oder größer als die
     * Chance ist.
     *
     * @param chance - Chance in Double, als Bsp. übergeben von 0.3 entspricht 30%.
     * @return true or false
     */
    public boolean hasGalaxyChance(double chance) {
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        return randomNumber < chance * 100;
    }
}
