package drunk.homebrew.forge.of.empires.app.service;

import java.util.List;
import java.util.Random;

import drunk.homebrew.forge.of.empires.app.persistence.Buildings;
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
	
	


	Buildings einsammeln(List<Buildings> buildings, Integer id, Boolean gBonus) {
		Buildings result = new Buildings();
		int faktor = 1;

		if(buildings.get(id).getName().equals("Grosser Leuchtturm")){
			Random rng = new Random();
			Integer zufallszahl = rng.nextInt(101);
			result = new BonusPool().poolLeuchtturm(buildings.get(id), zufallszahl);
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
