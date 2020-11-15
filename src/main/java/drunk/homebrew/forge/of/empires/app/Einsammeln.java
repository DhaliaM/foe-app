package drunk.homebrew.forge.of.empires.app;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Einsammeln {
	private static final Logger LOGGER = LoggerFactory.getLogger(Einsammeln.class);
	
	
	BonusChance bonus = new BonusChance();
	
	
	private int forgepoints;
	private int goods;
	private int units;
	private int medals;
	private int diamonds;
	private int production;
	private int coins;
	
	
	Buildings einsammeln(LoadProperties yamlData, String building) {
		Map<String, Buildings> productionBuildings = yamlData.getBuildings();
		Buildings targetBuilding = productionBuildings.get(building);
		List<Loot> bonusListe = targetBuilding.getLoot();
		Buildings result = new Buildings();
		Buildings lTurm = new BonusPool();
		int faktor = 1;
		
		
		
		
		
		if(productionBuildings.get(building).equals(productionBuildings.get("Leuchtturm"))){
			Random rng = new Random();
			Integer zufallszahl = rng.nextInt(101);
			
			result = result).poolLeuchtturm(productionBuildings.get("Leuchtturm", zufallszahl);
			
			
			LOGGER.error("L-Turm ist vorhanden " + lTurm.getForgepoints());
			
			
			
//			if(bonusListe.get(0).getChance() * 100 > zufallszahl) {
//				targetBuilding.setDiamonds(bonusListe.get(0).getAmount());
//				LOGGER.error("L-Turm ist vorhanden und es gab dias" + targetBuilding);
//			}
//			if(bonusListe.get(1).getChance() * 100 > zufallszahl) {
//				if(bonus.chance(0.5)) {
//					targetBuilding.setForgepoints(bonusListe.get(1).getAmount());
//					LOGGER.error("L-Turm ist vorhanden und es gab fp" + targetBuilding);
//				}
//				else
//				{
//					targetBuilding.setGoods(bonusListe.get(2).getAmount());
//					LOGGER.error("L-Turm ist vorhanden und es gab güter" + targetBuilding);
//				}
//			}
			
			
			
			
			
			
			
			
			
		}
		
		
		
		if(productionBuildings.get(building).isGalaxie()) {
			
			if(bonus.chance(0.0)) {
				faktor = 2;
			}
		}
		
			coins = targetBuilding.getCoins() * faktor;
			goods = targetBuilding.getGoods() * faktor;
			units = targetBuilding.getUnits() * faktor;
			medals = targetBuilding.getMedals() * faktor;
			forgepoints = targetBuilding.getForgepoints() * faktor;
			production = targetBuilding.getProduction() * faktor;
			diamonds = targetBuilding.getDiamonds() * faktor;
		
			result.setCoins(coins);
			result.setForgepoints(forgepoints);
			result.setGoods(goods);
			result.setUnits(units);
			result.setMedals(medals);
			result.setProduction(production);
			result.setDiamonds(diamonds);
			
		return result;
	}
}
