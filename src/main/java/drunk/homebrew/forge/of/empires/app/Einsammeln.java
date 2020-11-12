package drunk.homebrew.forge.of.empires.app;

import java.util.Map;

public class Einsammeln {
	BonusChance bonus = new BonusChance();
	
	
	private int forgepoints;
	private int goods;
	private int units;
	private int medals;
	
	private int production;
	private int coins;
	private boolean galaxie;
	
	Buildings einsammeln(LoadProperties einsammeln, String building) {
		Map<String, Buildings> productionBuildings = einsammeln.getBuildings();
		Buildings targetBuilding = productionBuildings.get(building);
		Buildings result = new Buildings();
		int faktor = 1;
		if(productionBuildings.get(building).isGalaxie()) {
			
			if(bonus.galaxieChance(0.3)) {
				faktor = 2;
			}
		}
		
			coins = targetBuilding.getCoins() * faktor;
			goods = targetBuilding.getGoods() * faktor;
			units = targetBuilding.getUnits() * faktor;
			medals = targetBuilding.getMedals() * faktor;
			forgepoints = targetBuilding.getForgepoints() * faktor;
			production = targetBuilding.getProduction() * faktor;
		
		
			result.setCoins(coins);
			result.setForgepoints(forgepoints);
			result.setGoods(goods);
			result.setUnits(units);
			result.setMedals(medals);
			result.setProduction(production);
			
			
		return result;
	}
}
