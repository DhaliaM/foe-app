package coffee.homebrew.forge.of.empires.app.service;

import java.util.List;
import java.util.Random;


import coffee.homebrew.forge.of.empires.app.persistence.BuildingEntity;

/**
 * Diese Klasse simuliert das einsammeln von einer Gebäudeproduktion, ggf. mit einem Bonus der Blauen Galaxie.
 *
 * @author Dhalia
 */
class Looting {

    private static final GalaxyChance BONUS = new GalaxyChance();

    /**
     * Berechnet den Ertrag der übergebenen Gebäudes.
     *
     * @param buildings   - Liste vom Typ Buildings, welche Gebäude enthält
     * @param id          - die id für das entsprechende Gebäude
     * @param galaxyBonus - Boolean, ob das Gebäude einen Galaxiebonus erhält oder nicht
     * @return Ergebnis vom Typ Buildings
     */
    BuildingEntity calculateLoot(List<BuildingEntity> buildings, int id, boolean galaxyBonus) {

        BuildingEntity result = new BuildingEntity();

        int factor = 1;

        if (galaxyBonus) {
            //Galaxiechance 0.3 entspricht 30%
            if (BONUS.hasGalaxyChance(0.3)) {
                factor = 2;
            }
        }

        int forgePoints = 0;
        int goods = 0;
        int units = 0;
        int medals = 0;
        int diamonds = 0;
        int production = 0;
        int coins = 0;
        ExtraLoot extraLoot = null;

        //wenn das Gebäude ein "Grosser Leuchtturm" ist, wird ein extra Loot Pool für dieses Gebäude benötigt
        for (BuildingEntity building : buildings) {
            if ("Grosser Leuchtturm".equals(building.getName())) {
                Random random = new Random();
                int randomNumber = random.nextInt(101);
                extraLoot.enrichWithRandomLighthouseLoot(building, randomNumber); //übergibt Leuchtturmobjekt
            }
            if (building.getId() == id) {
                coins = building.getCoins() * factor;
                goods = building.getGoods() * factor;
                units = building.getUnits() * factor;
                medals = building.getMedals() * factor;
                forgePoints = building.getForgePoints() * factor;
                production = building.getProduction() * factor;
                diamonds = building.getDiamonds() * factor;
            }
        }
        result.setCoins(coins);
        result.setForgePoints(forgePoints);
        result.setGoods(goods);
        result.setUnits(units);
        result.setMedals(medals);
        result.setProduction(production);
        result.setDiamonds(diamonds);

        return result;
    }
}
