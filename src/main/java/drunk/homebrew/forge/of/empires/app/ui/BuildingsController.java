package drunk.homebrew.forge.of.empires.app.ui;

import drunk.homebrew.forge.of.empires.app.persistence.Building;
import drunk.homebrew.forge.of.empires.app.persistence.BuildingRepository;
import drunk.homebrew.forge.of.empires.app.persistence.CalculationDto;
import drunk.homebrew.forge.of.empires.app.service.EvaluationOfIncome;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Ein Spring REST Controller für /EventBuildings.
 * Er baut die Seite über GET auf und schickt die Auswertung über POST in Json zurück.
 *
 * @author Dhalia
 */
@RestController
public class BuildingsController {

    private final BuildingRepository dbQuery;

    public BuildingsController(BuildingRepository dbQuery) {
        this.dbQuery = Objects.requireNonNull(dbQuery);
    }

    static List<Building> buildingListe = new ArrayList<>();

    @GetMapping(path = "/EventBuildings")
    public String buildWebsite() throws Exception {
        InputStreamReader isReader = new InputStreamReader(getClass().getResourceAsStream("/building.html"));
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
            if (str.contains("<!-- INSERT_HERE -->")) {
                buildingListe = dbQuery.loadAllBuildings();
                sb.append("<ul>\n");
                for (Building building : buildingListe) {
                    sb.append("<li>\n");
                    sb.append("<label>\n");
                    sb.append(building.getName());
                    sb.append(" <input type=\"number\" id=\"");
                    sb.append(building.getId());
                    sb.append("\" name=\"");
                    sb.append(building.getName());
                    sb.append("\" value=\"0\" size=\"1\" /\n>");
                    sb.append(" Anzahl Galaxiebonus ");
                    sb.append("<input type =\"number\" id=\"Galaxiebonus.");
                    sb.append(building.getId());
                    sb.append("\" value=\"0\" size=\"1\"/>\n");
                    sb.append("</label>\n");
                    sb.append("</li>\n");
                }
                sb.append("</ul>\n");
            } else {
                sb.append(str);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @PostMapping(path = "/EventBuildings", consumes = "application/json", produces = "application/json")
    public CalculationDto calculateLoot(@RequestBody List<BuildingDto> request) {
        EvaluationOfIncome calculation = new EvaluationOfIncome();
        buildingListe = dbQuery.loadAllBuildings();
        return calculation.calculate(request, buildingListe);
    }
}