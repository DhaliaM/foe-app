package drunk.homebrew.forge.of.empires.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.*;

public class AuswertungServlet {

    public String auswerten(Map<String, Map<String,String>> input) throws SQLException, NamingException {

//        Map<String, Map<String,String>> result = new ObjectMapper().readValue(input, Map.class);

        DbAnbindung dbAbfrage = new DbAnbindung();
        List<Buildings> buildingListe = new ArrayList<Buildings>();

        try {
            buildingListe = dbAbfrage.sqlAusgabe();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

        for(String key : input.keySet()){                                                                               // aus irgendeinbem grund hakts wohl ab hier mit dem Index
            int anzahl = Integer.parseInt(input.get(key).get("Anzahl"));
            int gBonus = Integer.parseInt(input.get(key).get("Galaxiebonus"));
            anzahl = anzahl - gBonus;
            id = Integer.parseInt(key);

            for(int i = 0;i < anzahl; i++){
                dailyIncome = income.einsammeln(buildingListe, id);

                dailyGoods = dailyGoods + dailyIncome.getGoods();

                dailyForgepoints = dailyForgepoints + dailyIncome.getForgepoints();
                dailyUnits = dailyUnits + dailyIncome.getUnits();
                dailyMedals = dailyMedals + dailyIncome.getMedals();
                dailyProduction = dailyProduction + dailyIncome.getProduction();
                dailyCoins = dailyCoins + dailyIncome.getCoins();
                dailyDiamonds = dailyDiamonds + dailyIncome.getDiamonds();


            }
            for(int j = gBonus; j > 0; j--){

                dailyIncome = income.einsammeln( buildingListe, id , true);

                dailyGoods = dailyGoods + dailyIncome.getGoods();

                dailyForgepoints = dailyForgepoints + dailyIncome.getForgepoints();
                dailyUnits = dailyUnits + dailyIncome.getUnits();
                dailyMedals = dailyMedals + dailyIncome.getMedals();
                dailyProduction = dailyProduction + dailyIncome.getProduction();
                dailyCoins = dailyCoins + dailyIncome.getCoins();
                dailyDiamonds = dailyDiamonds + dailyIncome.getDiamonds();
            }
        }

        Map<String,String> ergebnis = new HashMap();
        ergebnis.put("Forgepunkte", Integer.toString(dailyForgepoints));
        ergebnis.put("Güter", Integer.toString(dailyGoods));
        ergebnis.put("Vorrat", Integer.toString(dailyProduction));
        ergebnis.put("Einheiten", Integer.toString(dailyUnits));
        ergebnis.put("Münzen", Integer.toString(dailyCoins));
        ergebnis.put("Medaillen", Integer.toString(dailyMedals));
        ergebnis.put("Diamanten", Integer.toString(dailyDiamonds));


        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT );

        String jsonString = new String();
        try {
            jsonString = mapper.writeValueAsString(ergebnis);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

    return jsonString;
    }

}
