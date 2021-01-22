package drunk.homebrew.forge.of.empires.app;

import org.apache.logging.slf4j.Log4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbAnbindung {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbAnbindung.class);

    List<Buildings> buildingsListe = new ArrayList<Buildings>();

    String url = "jdbc:mariadb://localhost:3306/FoE1?zeroDateTimeBehavior=convertToNull";
    String databaseName = "FoE1";
    String userName = "FoE_User";
    String password = "roflcopter";

    Context ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/Foe");
    Connection connection = ds.getConnection();
//    Connection connection = DriverManager.getConnection(url, userName ,password);

    Statement sqlStatement = connection.createStatement();


    public List sqlAusgabe() throws SQLException {

        ResultSet result = sqlStatement.executeQuery("SELECT * FROM Spezialgebäude;");



        while(result.next()){
            Buildings building = new Buildings();
            building.setId(result.getInt("Id"));
            building.setName(result.getString("Name"));
            building.setForgepoints(result.getInt("Forgepunkte"));
            building.setGoods(result.getInt("Güter"));
            building.setProduction(result.getInt("Vorrat"));
            building.setUnits(result.getInt("Einheiten"));
            building.setCoins(result.getInt("Münzen"));
            building.setMedals(result.getInt("Medaillen"));
            building.setDiamonds(result.getInt("Diamanten"));
            buildingsListe.add(building);
            //LOGGER.error(result.getString("Name"));
        }
        return buildingsListe;
    }


    public DbAnbindung() throws SQLException, NamingException {
    }
}
