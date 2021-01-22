package drunk.homebrew.forge.of.empires.app;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingServlet extends HttpServlet {
    Einsammeln income = new Einsammeln();
    Buildings dailyIncome = new Buildings();

//    Yaml yaml = new Yaml(new Constructor(LoadProperties.class));
//    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("application.yaml");
//    LoadProperties yamlData = yaml.load(stream);



    DbAnbindung dbAbfrage = new DbAnbindung();
    List<Buildings> buildingListe = new ArrayList<Buildings>();




    public BuildingServlet() throws SQLException, NamingException {
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

//        Map<String, Buildings> eventBuildings = yamlData.getBuildings();


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        StringBuilder sb = new StringBuilder();


        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"de\">");
        sb.append("<head>");
        sb.append("<meta charset=\"utf-8\">");
        sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        sb.append("<title>Titel der Seite | Name der Website</title>");
        sb.append("<style>");
        sb.append(".wrapper { ");
        sb.append("display: grid;");
        sb.append("grid-template-columns: 40% 60%;");
        sb.append("gap: 3px;}");
        sb.append(".input { grid-column-start: 1; grid-column-end: 2; place-self: right; }");
        sb.append(".output { grid-column-start: 2; grid-column-end: 3; align-content: center; }");
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div class=\"wrapper\">");
        sb.append("<div class=\"input\">");
        sb.append("<form action=\"EventBuildings\" name=\"buildings\" id=\"buildings\">");
        sb.append("<ul>");

        try {
            buildingListe = dbAbfrage.sqlAusgabe();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        sb.append("console.log(");
//        sb.append("buildingListe");
//        sb.append(");");
        for(Buildings building : buildingListe){
            sb.append("<li>");
            sb.append("<label>");
            sb.append(building.getName());                                                                              //Label Name aus der map
            sb.append(" <input type=\"number\" id=\"");                                                                 //input Feld, Id basierned auf der Map
            sb.append(building.getId());
            sb.append("\" name=\"");
            sb.append(building.getName());
            sb.append("\" value=\"0\" size=\"1\">");
            sb.append(" Anzahl Galaxiebonus ");
            sb.append("<input type =\"number\" id=\"Galaxiebonus.");
            sb.append(building.getId());
            sb.append("\" value=\"0\" size=\"1\">");
            sb.append("</label>");

            sb.append("</li>");
        }
//        for (String key : eventBuildings.keySet()){                                                                     //daten aus der map, atm yaml
//            sb.append("<li>");
//            sb.append("<label>");
//            sb.append(eventBuildings.get(key).getName());                                                               //Label Name aus der map
//            sb.append(" <input type=\"number\" id=\"");                                                                 //input Feld, Id basierned auf der Map
//            sb.append(key);
//            sb.append("\" name=\"");
//            sb.append(eventBuildings.get(key).getName());
//            sb.append("\" value=\"0\" size=\"1\">");
//            sb.append(" Anzahl Galaxiebonus ");
//            sb.append("<input type =\"number\" id=\"Galaxiebonus.");
//            sb.append(key);
//            sb.append("\" value=\"0\" size=\"1\">");
//            sb.append("</label>");
//
//            sb.append("</li>");
//        }
        sb.append("<input type=\"Button\" id=\"submitButton\" onclick=\"submitJson()\" value=\"Submit\">");
        sb.append("</form>");
        sb.append("</div>");

        sb.append("<div id=\"output\" class=\"output\">");

        sb.append("Ausgabe");


        sb.append("</div>");



        sb.append("<script>");
        sb.append("const form = document.querySelector('form');");
        sb.append("form.addEventListener('submit', event => {event.preventDefault()});");

        //Funktion um die Json mit nested Map zu füllen
        sb.append("function mapToObjectRec(m) {");
        sb.append("let lo = {};");
        sb.append("for(let[k,v] of m){");
        sb.append("if(v instanceof Map){");
        sb.append("lo[k] = mapToObjectRec(v);");
        sb.append("}");
        sb.append("else {");
        sb.append("lo[k] = v;");
        sb.append("}");
        sb.append("}");
        sb.append("return lo;");
        sb.append("}");

        //Funktion um die Json zu füllen
        sb.append("function submitJson(){");
        sb.append("var dataForm = document.getElementById(\"buildings\");");
        sb.append("var dataInArray = new Map();");
        sb.append("for(let element of dataForm.elements){");
        sb.append("let shortId = element.id;");
        sb.append("shortId = shortId.split(\".\",1);");
        sb.append("if(shortId != \"Galaxiebonus\" && shortId != \"submitButton\"){");                                   //hier hängts nicht mehr
        sb.append("let inputData = new Map();");
        sb.append("inputData.set('Anzahl', element.value);");
        sb.append("inputData.set('Galaxiebonus', document.getElementById(\"Galaxiebonus.\" + element.id).value);");
        sb.append("dataInArray.set(element.id, inputData);");
        sb.append("}");                                                                                                 // schließt if statement
        sb.append("}");                                                                                                 // schließt for
        sb.append("console.log(dataInArray);");
        sb.append("var dataJson = mapToObjectRec(dataInArray);");
        sb.append("var dataJson = JSON.stringify(dataJson);");
        sb.append("var xhr = new XMLHttpRequest();");
        sb.append("xhr.onreadystatechange = function() {");
        sb.append("if (this.readyState == 4 && this.status == 200) {");
        sb.append("console.log(this.responseText);");                                                                   //enthält response von doPost
        sb.append("document.getElementById(\"output\").innerHTML = this.responseText;");
        sb.append("}");
        sb.append("};");
        sb.append("xhr.open(\"POST\", \"/FoE/EventBuildings\", true);");
        sb.append("xhr.setRequestHeader('Content-Type', 'application/json');");
        sb.append("xhr.send(dataJson);");
        sb.append("}");

        sb.append("document.getElementById(\"buildings\").onsubmit = function(){");
        sb.append("location.reload(true);");
        sb.append("}");

        sb.append("</script>");
        sb.append("</body>");
        sb.append("</html>");

        pw.println(sb);
        pw.close();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, Map<String,String>> result = new ObjectMapper().readValue(request.getInputStream(), Map.class);

        int dailyForgepoints = 0;
        int dailyGoods = 0;
        int dailyUnits = 0;
        int dailyMedals = 0;
        int dailyProduction = 0;
        int dailyCoins = 0;
        int dailyDiamonds = 0;

        int id = 0;

    for(String key : result.keySet()){
        int anzahl = Integer.parseInt(result.get(key).get("Anzahl"));
        int gBonus = Integer.parseInt(result.get(key).get("Galaxiebonus"));
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




        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("tägliche FP = "+ dailyForgepoints + " tägliche Einheiten = " + dailyUnits + " tägliche Diamanten = " + dailyDiamonds + " tägliche Medailien = " + dailyMedals + " tägliche Produktion = " + dailyProduction + " tägliche Münzen = " + dailyCoins + " tägliche Güter = " + dailyGoods);

        dailyForgepoints = 0;
        dailyGoods = 0;
        dailyUnits = 0;
        dailyMedals = 0;
        dailyProduction = 0;
        dailyCoins = 0;
        dailyDiamonds = 0;


        pw.close();


        response.sendRedirect("/FoE/EventBuildings");


    }
}
