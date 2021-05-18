package coffee.homebrew.forge.of.empires.app.legacy;

import coffee.homebrew.forge.of.empires.app.persistence.Building;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Laden der Gebäudedaten aus einer Datenbank mittels JdbcTemplate.
 * @author Dhalia
 */
@Component
public class BuildingRepository {

    private final JdbcTemplate jdbcTemplate;

    public BuildingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = Objects.requireNonNull(jdbcTemplate);
    }

    /**
     * @return eine Liste mit Building Objekten, alles aus der Tabelle "Spezialgebäude".
     * @author Dhalia
     */
    public List<Building> loadAllBuildings() {
        List<Building> buildingListe = new ArrayList<>();
        final String SQL = "SELECT * FROM Spezialgebäude;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQL);
        while (rowSet.next()) {
            Building building = new Building();
            building.setId(rowSet.getInt("Id"));
            building.setName(rowSet.getString("Name"));
            building.setForgePoints(rowSet.getInt("Forgepunkte"));
            building.setGoods(rowSet.getInt("Güter"));
            building.setProduction(rowSet.getInt("Vorrat"));
            building.setUnits(rowSet.getInt("Einheiten"));
            building.setCoins(rowSet.getInt("Münzen"));
            building.setMedals(rowSet.getInt("Medaillen"));
            building.setDiamonds(rowSet.getInt("Diamanten"));
            buildingListe.add(building);
        }
        return buildingListe;
    }
}
