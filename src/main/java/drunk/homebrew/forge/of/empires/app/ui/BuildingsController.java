//Q: Was sind das für Kommentare?
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
    //FIXME: Eher Bad-Practice, besser ein Konstruktor erstellen, wo die Instanzvariable von Spring gesetzt werden kann
    @Autowired
    //FIXME: Aktuell ist die Sichtbarkeit der Instanzvariable package-privat. Das bedeutet jede Klasse in dem selben Package könnte auf diese Zugreifen und auch manipulieren. Ist das gewollt?
    //FIXME: Im Quellcode sollte Denglisch vermieden werden, am besten für eins entscheiden. Ich habe bisher die Erfahrung gemacht, dass Denglisch nur verwendet wird, wenn man die fachlichen Begriffe nicht übersetzen will. Beispiel: Im Gasmarkt (Gasspeicher) gibt es den Speicher und Speicherkopplungspunkt. Diese könnte man deutsch lassen, da es manchmal schwierig ist eine korrekte Übersetzung zu finden oder sie sofort zu verstehen.
    DatabaseConnection dbAbfrage;

    //FIXME: Die Variable ist statisch, also teilt sich jedes Objekt der Klasse diese Variable. ist das gewollt? Weiterhin wird die Variable in jeder Methode neu gesetzt, was bedeutet, dass es bei Multithreading zu Problemen kommen könnte, wenn eine Schleife darüber erfolgt.
    //FIXME: Aktuell ist die Sichtbarkeit der statischen Variable public. Soll jeder auf diese Variable zugreifen können?
    //FIXME: Diamond-Operator bei Instanzierung von Collections, bzw. allgemein bei Klassen mit Generics, verwenden. Siehe https://codenuclear.com/diamond-operator-type-inference-generic-instance-creation-java
    public static List<Buildings> buildingListe = new ArrayList<Buildings>();

    @GetMapping(path = "/EventBuildings")
    //FIXME: Denglisch vermeiden
    public String seitenaufbau() throws Exception {
        //FIXME: Ich habe gesehen, du benutzt für die Fehlerseite schon Thymeleaf, dann könntest du das hier ja auch darauf umstellen.
        InputStreamReader isReader = new InputStreamReader(getClass().getResourceAsStream("/building.html"));
        BufferedReader reader = new BufferedReader(isReader);
        //FIXME: Es ist sinnvoll den StringBuilder zu verwenden, da dieser performanter ist. Der StringBuffer ist nur bei Multithreading sinnvoll, was bei einer lokalen Variable keine Rolle spielt.
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
                //FIXME: String zusammensetzen mit "+", wenn man einen Builder benutzt ist nicht unbedingt sinnvoll. Lieber sb.append(str).append("\n)
                sb.append(str + "\n");
            }
        }
        return sb.toString();
    }

    @PostMapping(path = "/EventBuildings", consumes = "application/json", produces = "application/json")
    //FIXME: Denglisch vermeiden
    //FIXME: Die Methodensignatur sagt, dass eine Exception geworfen wird, was aber nicht der Fall ist. Bitte entfernen.
    //FIXME: Anstatt eines Strings sollte eher ein DTO zurückgeben werden, Spring übernimmt für dich die Umwandlung in JSON.
    public String auswerten(@RequestBody List<BuildingDto> request) throws Exception {
        //FIXME: Denglisch vermeiden
        EvaluationOfIncome auswertung = new EvaluationOfIncome();
        buildingListe = dbAbfrage.databaseOutput();
        //FIXME: Die Variable kann deklariert und mit dem Ergebnis aus "auswertung.getEvaluation(...)" instanziert werden. Besser wäre natürlich gleich das Ergebnis von "auswertung.getEvaluation(...)" zurückzugeben.
        //INFO: Ein String sollte nicht mit new String() erstellt werden, sondern immer mit "". Weil sonst das Caching der VM nicht greift und du ihn zwingst neuen Speicherplatz zu allokieren.
        String ergebnis = new String();
        ergebnis = auswertung.getEvaluation(request,buildingListe);
        return ergebnis;
    }
}