package coffee.homebrew.forge.of.empires.app.service;

import java.util.Random;

/**
 * Diese Klasse überprüft nur ob eine Wahrscheinlichkeit eingetreten ist
 *
 * @author Dhalia
 */

class GalaxyChance {
    /**
     * Überprüft ob die übergebene Chance eingetreten ist.
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
