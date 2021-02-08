package drunk.homebrew.forge.of.empires.app;


import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.NamingException;


/**
 * Hauptklasse zum starten der App
 *
 * @author Dhalia
 *
 */

@SpringBootApplication
public class FoE {
//	private static final Logger LOGGER = LoggerFactory.getLogger(FoE.class);


//	private static String hochkommata(String s){
//		s = "'" + s + "'";
//		return s;
//	}


	public static void main(String[] args) throws JsonProcessingException, SQLException, NamingException {

        SpringApplication.run(FoE.class,args);


//		//BonusChance chance = new BonusChance();
//		Einsammeln sammlung = new Einsammeln();
////		Buildings specialBuildings = new Buildings(); //für Sql
////		Buildings dailyIncome = new Buildings();
//
//		DbAnbindung dbAbfrage = new DbAnbindung();
//
//		LOGGER.error(dbAbfrage.sqlAusgabe().toString());
//
//
//
//
//
//
//
//
//
//		int dailyForgepoints = 0;
//		int dailyGoods = 0;
//		int dailyUnits = 0;
//		int dailyMedals = 0;
//		int dailyProduction = 0;
//		int dailyCoins = 0;
//		int dailyDiamonds = 0;
//
//
//
//
//		String url = "jdbc:mariadb://localhost:3306/FoE1?zeroDateTimeBehavior=convertToNull";
//		String databaseName = "FoE1";
//		String userName = "FoE_User";
//		String password = "roflcopter";
//
//		Connection connection = DriverManager.getConnection(url, userName ,password);
//
//		String sql = new String();
//
////		for (String key : yamlData.getBuildings().keySet()) {
//////			LOGGER.error("key= " + specialBuildings.get(key).getName());
////			String name = specialBuildings.get(key).getName();
////			String fp = Integer.toString(specialBuildings.get(key).getForgepoints());
////			String goods = Integer.toString(specialBuildings.get(key).getGoods());
////			String units = Integer.toString(specialBuildings.get(key).getUnits());
////			String medals = Integer.toString(specialBuildings.get(key).getMedals());
////			String coins = Integer.toString(specialBuildings.get(key).getCoins());
////			String production = Integer.toString(specialBuildings.get(key).getProduction());
////			String diamonds = Integer.toString(specialBuildings.get(key).getDiamonds());
////
////			sql = "INSERT INTO Spezialgebäude (Name, Forgepunkte, Güter, Vorrat, Einheiten, Münzen, Medaillen, Diamanten) VALUES ("+ hochkommata(name)  + "," + hochkommata(fp) + "," + hochkommata(goods) + "," + hochkommata(production) + "," + hochkommata(units) + "," + hochkommata(coins) + "," + hochkommata(medals) + "," + hochkommata(diamonds) + ");";
////
////			Statement sqlStatement = connection.createStatement();
////			sqlStatement.executeUpdate(sql);
////			sqlStatement.close();
////
////		}
////		sql = "CREATE TABLE Spezialgebäude (Id INT NOT NULL AUTO_INCREMENT, Name VARCHAR(100) NOT NULL,Forgepunkte INT(3), Güter INT(4), Vorrat INT, Einheiten INT(3), Münzen INT, Medaillen INT, Diamanten INT(3) , PRIMARY KEY (Id));";
////		Statement sqlStatement = connection.createStatement();
////		sqlStatement.executeUpdate(sql);
////		sqlStatement.close();
//
//
//
////		for (String str : args) {
////			// convert into integer type
////			int argument = Integer.parseInt(str);
////			String s = Integer.toString(argument);
////
////
////
////			//LOGGER.error("Argument in integer form: " + s);
////
////			//dailyIncome = sammlung.einsammeln(yamlData, s);
////			for (String key : yamlData.getBuildings().keySet()) {
////
////				dailyIncome = sammlung.einsammeln(yamlData, key);
////				dailyGoods = dailyGoods + dailyIncome.getGoods();
////
////				dailyForgepoints = dailyForgepoints + dailyIncome.getForgepoints();
////				dailyUnits = dailyUnits + dailyIncome.getUnits();
////				dailyMedals = dailyMedals + dailyIncome.getMedals();
////				dailyProduction = dailyProduction + dailyIncome.getProduction();
////				dailyCoins = dailyCoins + dailyIncome.getCoins();
////				dailyDiamonds = dailyDiamonds + dailyIncome.getDiamonds();
////
////
////			}
////
////
////			try {
////
////
////				LOGGER.error("taegliche FP = " + dailyForgepoints);
////				LOGGER.error("taegliche Gueter = " + dailyGoods);
////				LOGGER.error("taegliche Einheiten = " + dailyUnits);
////				LOGGER.error("taegliche Medaillen = " + dailyMedals);
////				LOGGER.error("taeglicher Vorrat = " + dailyProduction);
////				LOGGER.error("taegliche Muenzen = " + dailyCoins);
////				LOGGER.error("taegliche Diamanten = " + dailyDiamonds);
////
////			} catch (IndexOutOfBoundsException e) {
////				LOGGER.error("Fehler innerhalb der Startargumente: ", e);
////			}
////		}
	}
}