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
 * @return eine Liste mit Buildings Objekten, alles aus der Tabelle "Spezialgebäude"
 * @author Dhalia
 *
 */
@Component
public class DatabaseConnection {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Buildings> databaseOutput() {
        List<Buildings> buildingsListe = new ArrayList<Buildings>();
        String sql = "SELECT * FROM Spezialgebäude;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
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
