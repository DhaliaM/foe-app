package drunk.homebrew.forge.of.empires.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import drunk.homebrew.forge.of.empires.app.persistence.Buildings;
//import drunk.homebrew.forge.of.empires.app.legacy.DbAnbindung;
import drunk.homebrew.forge.of.empires.app.ui.BuildingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.*;
@SpringBootApplication
public class AuswertungServlet {


    JdbcTemplate jdbcTemplate;

    public String auswerten(List <BuildingDto> input) throws SQLException, NamingException {

//        Map<String, Map<String,String>> result = new ObjectMapper().readValue(input, Map.class);

        String sql = "SELECT * FROM Spezialgebäude;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

//        DbAnbindung dbAbfrage = new DbAnbindung();
        List<Buildings> buildingListe = new ArrayList<Buildings>();

        while(rowSet.next()){
            Buildings building = new Buildings();
            building.setId(rowSet.getInt("Id"));
            building.setName(rowSet.getString("Name"));
            building.setForgepoints(rowSet.getInt("Forgepunkte"));
            building.setGoods(rowSet.getInt("Güter"));
            building.setProduction(rowSet.getInt("Vorrat"));
            building.setUnits(rowSet.getInt("Einheiten"));
            building.setCoins(rowSet.getInt("Münzen"));
            building.setMedals(rowSet.getInt("Medaillen"));
            building.setDiamonds(rowSet.getInt("Diamanten"));
            buildingListe.add(building);
        }


        Buildings dailyIncome = new Buildings();
        Einsammeln income = new Einsammeln();

        int dailyForgepoints = 0;
        int dailyGoods = 0;
        int dailyUnits = 0;
        int dailyMedals = 0;
        int dailyProduction = 0;
        int dailyCoins = 0;
        int dailyDiamonds = 0;

        int id = 0;

        for(BuildingDto dto : input){
            int anzahl = dto.getCount(); //Anzahl des jeweiligen Gebäudes
            int gBonus = dto.getBonus(); //Anzahl des jeweiligen Gebäudes mit Bonus

            id = dto.getId();

            for(int current = 0; current < anzahl; current++){

                final boolean isBonusIteration = current < gBonus;
                dailyIncome = income.einsammeln(buildingListe, id, isBonusIteration);

                dailyGoods = dailyGoods + dailyIncome.getGoods();
                dailyForgepoints = dailyForgepoints + dailyIncome.getForgepoints();
                dailyUnits = dailyUnits + dailyIncome.getUnits();
                dailyMedals = dailyMedals + dailyIncome.getMedals();
                dailyProduction = dailyProduction + dailyIncome.getProduction();
                dailyCoins = dailyCoins + dailyIncome.getCoins();
                dailyDiamonds = dailyDiamonds + dailyIncome.getDiamonds();
            }
        }

        Map<String,String> ergebnis = new HashMap<>();
        ergebnis.put("Forgepunkte", Integer.toString(dailyForgepoints));
        ergebnis.put("Güter", Integer.toString(dailyGoods));
        ergebnis.put("Vorrat", Integer.toString(dailyProduction));
        ergebnis.put("Einheiten", Integer.toString(dailyUnits));
        ergebnis.put("Münzen", Integer.toString(dailyCoins));
        ergebnis.put("Medaillen", Integer.toString(dailyMedals));
        ergebnis.put("Diamanten", Integer.toString(dailyDiamonds));


        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT );


        try {
            return mapper.writeValueAsString(ergebnis);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return "";
        }


    }

}
