//Q: Was sind das für Kommentare?
/**
 * @(#)ExtraLoot.java
 */
package drunk.homebrew.forge.of.empires.app.service;

import drunk.homebrew.forge.of.empires.app.persistence.Buildings;

/**
 * Diese Klasse enthält einen extra Loot Pool eines Gebäudes
 * FIXME: Ab hier gehört es an die Javadoc der Methode
 * @param poolObject - das betroffene Gebäude
 * @param randomNumber - Zufallszahl
 * @return Ergebnis vom Typ Buildings
 * FIXME: bis hier hin
 * @author Dhalia
 * FIXME: Unnötige Leerzeilen vermeiden
 *
 */
//FIXME: Unnötige Leerzeilen vermeiden

//FIXME: Wenn die Klasse nur von "Looting" verwendet werden soll, kann die Klasse auch "package-privat" sein
public class ExtraLoot {

    //FIXME: Denglisch vermeiden
    //FIXME: An Methodennamenskonvention halten. Beispiel: enrichWithRandomLeuchtturmValues oder enrichWithRandomLighthouseValues
    //FIXME: Javadoc von oben ergänzen
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
