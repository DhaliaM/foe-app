/**
 * @(#)Looting.java
 *
 */
package drunk.homebrew.forge.of.empires.app.service;

import java.util.List;
import java.util.Random;
import drunk.homebrew.forge.of.empires.app.persistence.Buildings;

/**
 * Diese Klasse simuliert das einsammeln von einer Gebäudeproduktion, ggf. mit einem Bonus der Blauen Galaxie.
 *
 * @param buildings - Liste vom Typ Buildings, welche Gebäude enthält
 * @param id - die id, Typ Integer für das entsprechende Gebäude
 * @param gBonus - Boolean, ob das Gebäude einen Galaxiebonus erhält oder nicht
 * @return Ergebnis vom Typ Buildings
 * @author Dhalia
 *
 */

public class Looting {

    GalaxieChance bonus = new GalaxieChance();
    private int forgepoints;
    private int goods;
    private int units;
    private int medals;
    private int diamonds;
    private int production;
    private int coins;

    Buildings getLoot(List<Buildings> buildings, Integer id, Boolean gBonus) {
        Buildings result = new Buildings();
        int faktor = 1;
        if (gBonus) {
            //Galaxiechance 0.3 entspricht 30%
            if (bonus.chance(0.3)) {
                faktor = 2;
            }
        }
        Integer counter = 0;
        //wenn das Gebäude ein Großer Leuchtturm ist, wird ein extra Loot Pool für dieses Gebäude benötigt
        for (Buildings building : buildings) {
            if (building.getName().equals("Grosser Leuchtturm")) {
                Random rng = new Random();
                Integer zufallszahl = rng.nextInt(101);
                result = new ExtraLoot().poolLeuchtturm(building, zufallszahl); //übergibt Leuchtturmobjekt
            }
            if (building.getId() == id) {

                coins = building.getCoins() * faktor;
                goods = building.getGoods() * faktor;
                units = building.getUnits() * faktor;
                medals = building.getMedals() * faktor;
                forgepoints = building.getForgepoints() * faktor;
                production = building.getProduction() * faktor;
                diamonds = building.getDiamonds() * faktor;
            }
        }
        result.setCoins(coins);
        result.setForgepoints(forgepoints);
        result.setGoods(goods);
        result.setUnits(units);
        result.setMedals(medals);
        result.setProduction(production);
        result.setDiamonds(diamonds);

        return result;
    }
}
