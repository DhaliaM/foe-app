/**
 * @(#)BuildingServlet.java
 *
 */
//package coffee.homebrew.forge.of.empires.app.legacy;
//
//import coffee.homebrew.forge.of.empires.app.persistence.Buildings;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
/**
 * Legacy Klasse, Anbindung einer MariaDB, entfällt mit Spring
 *
 * @author Dhalia
 *
 */
//public class DbAnbindung {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DbAnbindung.class);
//
//    List<Buildings> buildingsListe = new ArrayList<Buildings>();
//
//    Context ctx = new InitialContext();
//    DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/Foe");
//    Connection connection = ds.getConnection();
//
//
//    Statement sqlStatement = connection.createStatement();
//
//
//    public List<Buildings> sqlAusgabe() throws SQLException {
//
//        ResultSet result = sqlStatement.executeQuery("SELECT * FROM Spezialgebäude;");
//
//
//
//        while(result.next()){
//            Buildings building = new Buildings();
//            building.setId(result.getInt("Id"));
//            building.setName(result.getString("Name"));
//            building.setForgepoints(result.getInt("Forgepunkte"));
//            building.setGoods(result.getInt("Güter"));
//            building.setProduction(result.getInt("Vorrat"));
//            building.setUnits(result.getInt("Einheiten"));
//            building.setCoins(result.getInt("Münzen"));
//            building.setMedals(result.getInt("Medaillen"));
//            building.setDiamonds(result.getInt("Diamanten"));
//            buildingsListe.add(building);
//        }
//        return buildingsListe;
//    }
//
//
//    public DbAnbindung() throws SQLException, NamingException {
//    }
//}
