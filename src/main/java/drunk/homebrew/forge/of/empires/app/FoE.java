package drunk.homebrew.forge.of.empires.app;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;




/**
 * Hauptklasse zum starten der App
 * 
 * @author b13r/Dhalia
 *
 */

public class FoE {
	private static final Logger LOGGER = LoggerFactory.getLogger(FoE.class);
	
	public static void main(String[] args) {
		BonusChance chance = new BonusChance();
		Einsammeln sammlung = new Einsammeln();
		
		Buildings blubb = new Buildings();
		
		int dailyForgepoints = 0;
		int dailyGoods = 0;
		int dailyUnits = 0;
		int dailyMedals = 0;
		int dailyProduction = 0;
		int dailyCoins = 0;
		
		//chance.galaxieChance((float) 0.4);
		
		Yaml yaml = new Yaml(new Constructor(LoadProperties.class));
		InputStream stream = FoE.class.getClassLoader().getResourceAsStream("application.yaml");
		LoadProperties yamlData = yaml.load(stream);
		
		
		
		
		//dailyForgepoints = sammlung.einsammeln(yamlData, yamlData.getBuildings().keySet().);
		
		try {
		Long id = yamlData.getBuildings().get("Leuchtturm").getLoot().get(0).getId();
		Bonus test =	yamlData.getBonus().get(id);
		
		
		for(String key: yamlData.getBuildings().keySet()) {
			dailyForgepoints = dailyForgepoints + sammlung.einsammeln(yamlData, key).getForgepoints();
		    dailyGoods = dailyGoods + sammlung.einsammeln(yamlData, key).getGoods();
		    dailyUnits = dailyUnits + sammlung.einsammeln(yamlData, key).getUnits();
		    dailyMedals = dailyMedals + sammlung.einsammeln(yamlData, key).getMedals();
		    dailyProduction = dailyProduction + sammlung.einsammeln(yamlData, key).getProduction();
		    dailyCoins = dailyCoins + sammlung.einsammeln(yamlData, key).getCoins();
		}
		//LOGGER.error("Inhalt der Map buildings" + test.getName());
		//LOGGER.error("Galaxiechance " + chance.galaxieChance(0.4));
		//LOGGER.error("Rss einsammeln vom " + yamlData.getBuildings());
		//LOGGER.error("Rss einsammeln vom " + yamlData.getBuildings().keySet());
		LOGGER.error("tägliche FP = " + dailyForgepoints);
		LOGGER.error("tägliche Güter = " + dailyGoods);
		LOGGER.error("tägliche Einheiten = " + dailyUnits);
		LOGGER.error("tägliche Medaillen = " + dailyMedals);
		LOGGER.error("täglicher Vorrat = " + dailyProduction);
		LOGGER.error("tägliche Münzen = " + dailyCoins);
		
		}
		catch (IndexOutOfBoundsException e) {
			LOGGER.error("Fehler innerhalb der Startargumente: ", e);
		}
	}
}
