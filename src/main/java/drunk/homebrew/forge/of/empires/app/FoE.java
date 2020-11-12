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
		//chance.galaxieChance((float) 0.4);
		
		Yaml yaml = new Yaml(new Constructor(LoadProperties.class));
		InputStream stream = FoE.class.getClassLoader().getResourceAsStream("application.yaml");
		LoadProperties yamlData = yaml.load(stream);
		
		
		
		try {
		Long id = yamlData.getBuildings().get("Leuchtturm").getLoot().get(0).getId();
		Bonus test =	yamlData.getBonus().get(id);
		
		
		//LOGGER.error("Inhalt der Map buildings" + test.getName());
		//LOGGER.error("Galaxiechance " + chance.galaxieChance(0.4));
		LOGGER.error("Rss einsammeln vom HippoCarceres " + sammlung.einsammeln(yamlData, "HippodromCarceres"));
		}
		catch (IndexOutOfBoundsException e) {
			LOGGER.error("Fehler innerhalb der Startargumente: ", e);
		}
	}
}
