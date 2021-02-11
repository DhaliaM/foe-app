//Q: Was sind das für Kommentare?
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
 * FIXME: Ab hier gehört es an die Javadoc der Methode
 * @param buildings - Liste vom Typ Buildings, welche Gebäude enthält
 * @param id - die id, Typ Integer für das entsprechende Gebäude
 * @param gBonus - Boolean, ob das Gebäude einen Galaxiebonus erhält oder nicht
 * @return Ergebnis vom Typ Buildings
 * FIXME: bis hier hin
 * @author Dhalia
 * FIXME: Unnötige Leerzeilen vermeiden
 *
 */
//FIXME: Unnötige Leerzeilen vermeiden

//FIXME: Wenn die Klasse nur von "EvaluationOfIncome" verwendet werden soll, kann die Klasse auch "package-privat" sein
public class Looting {

    //FIXME: Aktuell ist die Sichtbarkeit der Instanzvariable package-privat. Das bedeutet jede Klasse in dem selben Package könnte auf diese Zugreifen und auch manipulieren. Ist das gewollt?
    //FIXME: Instanzvariablen, die nach der Instanzierung nicht mehr veränderbar sein sollen, können auch mit "final" versehen werden. Dann kann dieser keine neue Instanz zu gewiesen werden. Beispiel: private final GalaxieChance
    GalaxieChance bonus = new GalaxieChance();

    //FIXME: Instanzvariablen sind an der Stelle nicht notwendig und führen bei Verwendung der selben Instanz zu fehlerhaften Verhalten bei Multi-Threading. lokale Variablen reichen vollkommen aus
    private int forgepoints;
    private int goods;
    private int units;
    private int medals;
    private int diamonds;
    private int production;
    private int coins;

    //FIXME: Variable "gBonus" sprechender bezeichnen. Beispiel: galaxyBonus
    //FIXME: Die Verwendung der Wrapper-Klassen (Boolean, Integer, Double, Long, Byte) ist nur notwendig, wenn diese "null" sein können. Ansonsten sollte man immer die primitiven Klassen (boolean, int, double, long, byte) verwenden, da diese wesentlich performanter sind und nie "null" sein können.
    //FIXME: "get" sollte nur verwendet werden, wenn man den Wert einer Instanzvariable zurückgibt. Bei Berechnungen wäre "calculate" besser.
    Buildings getLoot(List<Buildings> buildings, Integer id, Boolean gBonus) {
        Buildings result = new Buildings();
        //FIXME: Denglisch vermeiden
        int faktor = 1;
        if (gBonus) {
            //Galaxiechance 0.3 entspricht 30%
            if (bonus.chance(0.3)) {
                faktor = 2;
            }
        }
        //FIXME: Variable wird nicht verwendet und kann entfernt werden
        Integer counter = 0;
        //wenn das Gebäude ein Großer Leuchtturm ist, wird ein extra Loot Pool für dieses Gebäude benötigt
        for (Buildings building : buildings) {
            //FIXME: String vergleiche sollte man immer Andersrum durchführen um eine NPE zu vermeiden, wenn "getName()" null zurückgibt. ""Grosser Leuchtturm".equals(building.getName())"
            if (building.getName().equals("Grosser Leuchtturm")) {
                //FIXME: Variablenname "rng" ist nicht sehr sprechend
                Random rng = new Random();
                //FIXME: Denglisch vermeiden
                //FIXME: Mit primitiven Typen arbeiten, da auch ein primitiver Typ zurückgegeben wird
                Integer zufallszahl = rng.nextInt(101);
                //FIXME: Der Rückgabewert hat keine Auswirkung, weil später alles über die Set-Methoden überschrieben wird. Im Grunde wird doch nur das "building"-Objekt manipuliert. Die Methode sollte keinen Rückgabewert (void) haben.
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
