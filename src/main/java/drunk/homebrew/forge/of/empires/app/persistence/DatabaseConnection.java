//Q: Was sind das für Kommentare?
/**
 * @(#)DatabaseConnection.java
 *
 */
package drunk.homebrew.forge.of.empires.app.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Datenbankanbindung über JdbcTemplate
 * FIXME: Sollte an die Methode ran
 * @return eine Liste mit Buildings Objekten, alles aus der Tabelle "Spezialgebäude"
 * @author Dhalia
 * FIXME: Unnötige Leerzeilen vermeiden
 *
 */
@Component
//FIXME: Ich würde anhand des Klassenamens erwarten, dass es sich um eine Datenbankverbindung handelt, also wie die Klasse "MariaDbConnection". Eigentlich ist die Klasse dafür da, dass man Datenbank-Operationen für die Gebäude durchführt. Ein guter Name wäre BuildingRepository.
public class DatabaseConnection {

    //FIXME: Eher Bad-Practice, besser ein Konstruktor erstellen, wo die Instanzvariable von Spring gesetzt werden kann
    @Autowired
    //FIXME: Aktuell ist die Sichtbarkeit der Instanzvariable package-privat. Das bedeutet jede Klasse in dem selben Package könnte auf diese Zugreifen und auch manipulieren. Ist das gewollt?
    JdbcTemplate jdbcTemplate;

    //FIXME: Javadoc fehlt
    //FIXME: Man sollte einem sprechenden Methodennamen wählen, wie zum Beispiel: "loadAllBuildings"
    public List<Buildings> databaseOutput() {
        //FIXME: Diamond-Operator bei Instanzierung von Collections, bzw. allgemein bei Klassen mit Generics, verwenden. Siehe https://codenuclear.com/diamond-operator-type-inference-generic-instance-creation-java
        List<Buildings> buildingsListe = new ArrayList<Buildings>();
        //FIXME: Ein String der immer wieder benötigt wird und sich kaum ändert, könnte auch eine Konstante sein.
        String sql = "SELECT * FROM Spezialgebäude;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        //FIXME: Die Einrückung passt nicht
            while(rowSet.next()){
                Buildings building = new Buildings();
                building.setId(rowSet.getInt("Id"));
                building.setName(rowSet.getString("Name"));
                building.setForgepoints(rowSet.getInt("Forgepunkte"));
                building.setGoods(rowSet.getInt("Güter"));
                building.setProduction(rowSet.getInt("Vorrat"));
                building.setUnits(rowSet.getInt("Einheiten"));
                building.setCoins(rowSet.getInt("Münzen"));
                building.setMedals(rowSet.getInt("Medaillen"));
                building.setDiamonds(rowSet.getInt("Diamanten"));
                buildingsListe.add(building);
            }
        return buildingsListe;
    }
}
