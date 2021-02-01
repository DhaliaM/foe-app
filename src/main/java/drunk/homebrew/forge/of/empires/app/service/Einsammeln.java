package drunk.homebrew.forge.of.empires.app.service;

import java.util.List;
import java.util.ListIterator;
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

		ListIterator<Buildings> buildingsIterator = buildings.listIterator(0);


		if(gBonus) {
			//Galaxiechance
			if(bonus.chance(0.3)) {
				faktor = 2;
			}
		}


		Boolean noHit = true;
		Integer counter = 0;

		while(buildingsIterator.hasNext() && noHit) {
			Buildings buildingItr = buildings.get(counter);
			if(buildingItr.getName().equals("Grosser Leuchtturm")){
				Random rng = new Random();
				Integer zufallszahl = rng.nextInt(101);
				result = new BonusPool().poolLeuchtturm(buildings.get(counter), zufallszahl);
			}

				if (buildingItr.getId() == id) {

					coins = buildingItr.getCoins() * faktor;
					goods = buildingItr.getGoods() * faktor;
					units = buildingItr.getUnits() * faktor;
					medals = buildingItr.getMedals() * faktor;
					forgepoints = buildingItr.getForgepoints() * faktor;
					production = buildingItr.getProduction() * faktor;
					diamonds = buildingItr.getDiamonds() * faktor;
					noHit = false;
				}
				else {
					counter++;
				}
		}



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
