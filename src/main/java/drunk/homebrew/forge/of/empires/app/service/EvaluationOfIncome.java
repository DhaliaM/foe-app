//Q: Was sind das für Kommentare?
/**
 * @(#)EvaluationOfIncome.java
 *
 */
package drunk.homebrew.forge.of.empires.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import drunk.homebrew.forge.of.empires.app.persistence.Buildings;
import drunk.homebrew.forge.of.empires.app.ui.BuildingDto;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * Auswertung des Einkommens der gewählten Gebäude
 * FIXME: Ab hier gehört es an die Javadoc der Methode
 * @param input Liste vom Typ BuildingDto welches die gewählten Gebäude enthält und
 * @param base eine Liste vom Typ Buildings, welche die Basiswerte aller Gebäude enthält.
 * @return Json String mit dem Ergebnis
 * FIXME: bis hier hin
 * @author Dhalia
 * FIXME: Unnötige Leerzeilen vermeiden
 *
 */
//FIXME: Unnötige Leerzeilen vermeiden

@Component
public class EvaluationOfIncome {
    //FIXME: Javadoc fehlt
    //FIXME: "get" sollte nur verwendet werden, wenn man den Wert einer Instanzvariable zurückgibt. Bei Berechnungen wäre "calculate" besser.
    public String getEvaluation(List <BuildingDto> input, List<Buildings> base) {
        Buildings dailyIncome;
        Looting income = new Looting();
        int dailyForgepoints = 0;
        int dailyGoods = 0;
        int dailyUnits = 0;
        int dailyMedals = 0;
        int dailyProduction = 0;
        int dailyCoins = 0;
        int dailyDiamonds = 0;
        int id = 0;

        for(BuildingDto dto : input){
            int anzahl = dto.getCount(); //Anzahl des jeweiligen Gebäudes
            int gBonus = dto.getBonus(); //Anzahl des jeweiligen Gebäudes mit Bonus
            id = dto.getId();
            for(int current = 0; current < anzahl; current++){
                final boolean isBonusIteration = current < gBonus;
                dailyIncome = income.getLoot(base, id, isBonusIteration);
                dailyGoods = dailyGoods + dailyIncome.getGoods();
                dailyForgepoints = dailyForgepoints + dailyIncome.getForgepoints();
                dailyUnits = dailyUnits + dailyIncome.getUnits();
                dailyMedals = dailyMedals + dailyIncome.getMedals();
                dailyProduction = dailyProduction + dailyIncome.getProduction();
                dailyCoins = dailyCoins + dailyIncome.getCoins();
                dailyDiamonds = dailyDiamonds + dailyIncome.getDiamonds();
            }
        }

        Map<String,String> ergebnis = new HashMap<>();
        ergebnis.put("Forgepunkte", Integer.toString(dailyForgepoints));
        ergebnis.put("Güter", Integer.toString(dailyGoods));
        ergebnis.put("Vorrat", Integer.toString(dailyProduction));
        ergebnis.put("Einheiten", Integer.toString(dailyUnits));
        ergebnis.put("Münzen", Integer.toString(dailyCoins));
        ergebnis.put("Medaillen", Integer.toString(dailyMedals));
        ergebnis.put("Diamanten", Integer.toString(dailyDiamonds));

        //FIXME: Mit Spring-Web musst du dich nicht mehr um die Umwandlung in JSON befassen, sondern mit DTO-Klassen arbeiten.
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT );
        try {
            return mapper.writeValueAsString(ergebnis);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return "";
        }
    }
}
