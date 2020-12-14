package drunk.homebrew.forge.of.empires.app;

import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
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

	public static void main(String[] args) throws JsonProcessingException {
		//BonusChance chance = new BonusChance();
		Einsammeln sammlung = new Einsammeln();

		Buildings dailyIncome = new Buildings();

		int dailyForgepoints = 0;
		int dailyGoods = 0;
		int dailyUnits = 0;
		int dailyMedals = 0;
		int dailyProduction = 0;
		int dailyCoins = 0;
		int dailyDiamonds = 0;


		Yaml yaml = new Yaml(new Constructor(LoadProperties.class));
		InputStream stream = FoE.class.getClassLoader().getResourceAsStream("application.yaml");
		LoadProperties yamlData = yaml.load(stream);
//
//		MapToJson mtj = new MapToJson();
//		String jsonString = mtj.mapToJson(yamlData);

		TransmitBuildings transmitBuildings = new TransmitBuildings();
		String buildings = transmitBuildings.transmitBuildings();

		LOGGER.error("Inhalt des Json Objects " + buildings);


		for (String str : args) {
			// convert into integer type
			int argument = Integer.parseInt(str);
			String s = Integer.toString(argument);

//			if(s == "1"){
//				//do somthing
//			}

			//LOGGER.error("Argument in integer form: " + s);

			//dailyIncome = sammlung.einsammeln(yamlData, s);
			for (String key : yamlData.getBuildings().keySet()) {

				dailyIncome = sammlung.einsammeln(yamlData, key);
				dailyGoods = dailyGoods + dailyIncome.getGoods();

				dailyForgepoints = dailyForgepoints + dailyIncome.getForgepoints();
				dailyUnits = dailyUnits + dailyIncome.getUnits();
				dailyMedals = dailyMedals + dailyIncome.getMedals();
				dailyProduction = dailyProduction + dailyIncome.getProduction();
				dailyCoins = dailyCoins + dailyIncome.getCoins();
				dailyDiamonds = dailyDiamonds + dailyIncome.getDiamonds();


			}


			try {


				LOGGER.error("taegliche FP = " + dailyForgepoints);
				LOGGER.error("taegliche Gueter = " + dailyGoods);
				LOGGER.error("taegliche Einheiten = " + dailyUnits);
				LOGGER.error("taegliche Medaillen = " + dailyMedals);
				LOGGER.error("taeglicher Vorrat = " + dailyProduction);
				LOGGER.error("taegliche Muenzen = " + dailyCoins);
				LOGGER.error("taegliche Diamanten = " + dailyDiamonds);

			} catch (IndexOutOfBoundsException e) {
				LOGGER.error("Fehler innerhalb der Startargumente: ", e);
			}
		}
	}
}