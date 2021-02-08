package drunk.homebrew.forge.of.empires.app.ui;

import drunk.homebrew.forge.of.empires.app.persistence.Buildings;
import drunk.homebrew.forge.of.empires.app.persistence.DbSpringAnbindung;
import drunk.homebrew.forge.of.empires.app.service.AuswertungServlet;
import org.springframework.web.bind.annotation.*;

import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BuildingsController {

    @GetMapping(path = "EventBuildings", produces="text/plain")
    public String seitenaufbau() throws Exception {


        DbSpringAnbindung dbAbfrage = new DbSpringAnbindung();


        List<Buildings> buildingListe = new ArrayList<Buildings>();

        InputStreamReader isReader = new InputStreamReader(getClass().getResourceAsStream("/classes/building.html"));
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str = reader.readLine()) != null) {
            if (str.contains("<!-- INSERT_HERE -->")) {
                try {
                    buildingListe = dbAbfrage.sqlAusgabe();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
    public String auswerten(@RequestBody List<BuildingDto> request) throws SQLException, NamingException {


        AuswertungServlet auswertung = new AuswertungServlet();


        String ergebnis = new String();
        try {
            ergebnis = auswertung.auswerten(request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (
                NamingException e) {
            e.printStackTrace();
        }


        return ergebnis;
    }
}