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
	
	
	Buildings einsammeln(List<Buildings> buildings, Integer id) {
//		Map<String, Buildings> productionBuildings = yamlData.getBuildings();

		//List<Loot> bonusListe = targetBuilding.getLoot();



		Buildings result = new Buildings();
//		//Buildings lTurm = new BonusPool();
		int faktor = 1;



		if(buildings.get(id).getName().equals("Leuchtturm")){
			Random rng = new Random();
			Integer zufallszahl = rng.nextInt(101);

			result = new BonusPool().poolLeuchtturm(buildings.get(id), zufallszahl);


			//LOGGER.error("L-Turm ist vorhanden " + result);

		}



//		if(productionBuildings.get(building).isGalaxie()) {
//			//Galaxiechance
//			if(bonus.chance(0.3)) {
//				faktor = 2;
//			}
//		}

			coins = buildings.get(id).getCoins() * faktor;
			goods = buildings.get(id).getGoods() * faktor;
			units = buildings.get(id).getUnits() * faktor;
			medals = buildings.get(id).getMedals() * faktor;
			forgepoints = buildings.get(id).getForgepoints() * faktor;
			production = buildings.get(id).getProduction() * faktor;
			diamonds = buildings.get(id).getDiamonds() * faktor;

			result.setCoins(coins);
			result.setForgepoints(forgepoints);
			result.setGoods(goods);
			result.setUnits(units);
			result.setMedals(medals);
			result.setProduction(production);
			result.setDiamonds(diamonds);

		return result;
	}

	Buildings einsammeln(List<Buildings> buildings, Integer id, Boolean gBonus) {
//		Map<String, Buildings> productionBuildings = yamlData.getBuildings();
//		Buildings targetBuilding = productionBuildings.get(building);
		//List<Loot> bonusListe = targetBuilding.getLoot();
		Buildings result = new Buildings();
		//Buildings lTurm = new BonusPool();
		int faktor = 1;





		if(buildings.get(id).getName().equals("Leuchtturm")){
			Random rng = new Random();
			Integer zufallszahl = rng.nextInt(101);

			result = new BonusPool().poolLeuchtturm(buildings.get(id), zufallszahl);


			//LOGGER.error("L-Turm ist vorhanden " + result);

		}



		if(gBonus) {
			//Galaxiechance
			if(bonus.chance(0.3)) {
				faktor = 2;
			}
		}

		coins = buildings.get(id).getCoins() * faktor;
		goods = buildings.get(id).getGoods() * faktor;
		units = buildings.get(id).getUnits() * faktor;
		medals = buildings.get(id).getMedals() * faktor;
		forgepoints = buildings.get(id).getForgepoints() * faktor;
		production = buildings.get(id).getProduction() * faktor;
		diamonds = buildings.get(id).getDiamonds() * faktor;

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
