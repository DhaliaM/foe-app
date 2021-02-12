package drunk.homebrew.forge.of.empires.app.service;

import drunk.homebrew.forge.of.empires.app.persistence.Building;

/**
 * Diese Klasse enthält einen extra Loot Pool eines Gebäudes.
 *
 * @author Dhalia
 */

class ExtraLoot {
    /**
     * @param poolObject   - das betroffene Gebäude
     * @param randomNumber - Zufallszahl
     * @return Ergebnis vom Typ Buildings
     */
    public Building enrichWithRandomLighthouseLoot(Building poolObject, int randomNumber) {

        if (randomNumber > 60) {
            poolObject.setMedals(1900);
        } else if (randomNumber > 20) {
            poolObject.setGoods(50);
        } else if (randomNumber > 5) {
            poolObject.setForgePoints(20);
        } else {
            poolObject.setDiamonds(25);
        }
        return poolObject;
    }
}
