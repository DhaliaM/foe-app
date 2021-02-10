/**
 * @(#)BuildingsController.java
 *
 */

package drunk.homebrew.forge.of.empires.app.ui;

import drunk.homebrew.forge.of.empires.app.persistence.Buildings;
import drunk.homebrew.forge.of.empires.app.persistence.DatabaseConnection;
import drunk.homebrew.forge.of.empires.app.service.EvaluationOfIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * Spring REST Controller für /EventBuildings
 * baut die Seite über GET auf und schickt die Auswertung über POST in Json zurück
 *
 * @author Dhalia
 *
 */
@RestController
public class BuildingsController {
    @Autowired
    DatabaseConnection dbAbfrage;

    public static List<Buildings> buildingListe = new ArrayList<Buildings>();

    @GetMapping(path = "/EventBuildings")
    public String seitenaufbau() throws Exception {
        InputStreamReader isReader = new InputStreamReader(getClass().getResourceAsStream("/building.html"));
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str = reader.readLine()) != null) {
            if (str.contains("<!-- INSERT_HERE -->")) {
                buildingListe = dbAbfrage.databaseOutput();
                sb.append("<ul>\n");
                for (Buildings building : buildingListe) {
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
                sb.append(str + "\n");
            }
        }
        return sb.toString();
    }

    @PostMapping(path = "/EventBuildings", consumes = "application/json", produces = "application/json")
    public String auswerten(@RequestBody List<BuildingDto> request) throws Exception {
        EvaluationOfIncome auswertung = new EvaluationOfIncome();
        buildingListe = dbAbfrage.databaseOutput();
        String ergebnis = new String();
        ergebnis = auswertung.getEvaluation(request,buildingListe);
        return ergebnis;
    }
}