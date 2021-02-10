/**
 * @(#)ExtraLoot.java
 */
package drunk.homebrew.forge.of.empires.app.service;

import drunk.homebrew.forge.of.empires.app.persistence.Buildings;

/**
 * Diese Klasse enthält einen extra Loot Pool eines Gebäudes
 *
 * @param poolObject - das betroffene Gebäude
 * @param randomNumber - Zufallszahl
 * @return Ergebnis vom Typ Buildings
 * @author Dhalia
 *
 */

public class ExtraLoot {

    public Buildings poolLeuchtturm(Buildings poolObject, int randomNumber) {

        if (randomNumber > 60) {
            poolObject.setMedals(1900);
        } else if (randomNumber > 20) {
            poolObject.setGoods(50);
        } else if (randomNumber > 5) {
            poolObject.setForgepoints(20);
        } else {
            poolObject.setDiamonds(25);
        }
        return poolObject;
    }
}
