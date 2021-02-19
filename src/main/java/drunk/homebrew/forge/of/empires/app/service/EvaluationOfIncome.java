package drunk.homebrew.forge.of.empires.app.service;

import drunk.homebrew.forge.of.empires.app.persistence.BuildingEntity;
import drunk.homebrew.forge.of.empires.app.ui.CalculationDto;
import drunk.homebrew.forge.of.empires.app.ui.BuildingDto;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Auswertung des Einkommens der gewählten Gebäude.
 *
 * @author Dhalia
 */

@Component
public class EvaluationOfIncome {

    /**
     * @param input Liste vom Typ BuildingDto welches die gewählten Gebäude enthält und
     * @param base  eine Liste vom Typ Buildings, welche die Basiswerte aller Gebäude enthält.
     * @return Objekt vom Typ CalculationDto
     */
    public CalculationDto calculate(List<BuildingDto> input, List<BuildingEntity> base) {
        BuildingEntity dailyIncome;
        Looting income = new Looting();
        int dailyForgepoints = 0;
        int dailyGoods = 0;
        int dailyUnits = 0;
        int dailyMedals = 0;
        int dailyProduction = 0;
        int dailyCoins = 0;
        int dailyDiamonds = 0;
        int id = 0;

        for (BuildingDto dto : input) {
            int anzahl = dto.getCount(); //Anzahl des jeweiligen Gebäudes
            int gBonus = dto.getBonus(); //Anzahl des jeweiligen Gebäudes mit Bonus
            id = dto.getId();
            for (int current = 0; current < anzahl; current++) {
                final boolean isBonusIteration = current < gBonus;
                dailyIncome = income.calculateLoot(base, id, isBonusIteration);
                dailyGoods = dailyGoods + dailyIncome.getGoods();
                dailyForgepoints = dailyForgepoints + dailyIncome.getForgePoints();
                dailyUnits = dailyUnits + dailyIncome.getUnits();
                dailyMedals = dailyMedals + dailyIncome.getMedals();
                dailyProduction = dailyProduction + dailyIncome.getProduction();
                dailyCoins = dailyCoins + dailyIncome.getCoins();
                dailyDiamonds = dailyDiamonds + dailyIncome.getDiamonds();
            }
        }
        CalculationDto ergebnis = new CalculationDto();
        ergebnis.setForgePoints(dailyForgepoints);
        ergebnis.setCoins(dailyCoins);
        ergebnis.setDiamonds(dailyDiamonds);
        ergebnis.setGoods(dailyGoods);
        ergebnis.setProduction(dailyProduction);
        ergebnis.setMedals(dailyMedals);
        ergebnis.setUnits(dailyUnits);
        return ergebnis;
    }
}
