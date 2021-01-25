package drunk.homebrew.forge.of.empires.app.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import drunk.homebrew.forge.of.empires.app.persistence.Buildings;
import drunk.homebrew.forge.of.empires.app.persistence.DbAnbindung;
import drunk.homebrew.forge.of.empires.app.service.AuswertungServlet;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildingServlet extends HttpServlet {
//    Einsammeln income = new Einsammeln();
//    Buildings dailyIncome = new Buildings();

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


        InputStreamReader isReader = new InputStreamReader(getClass().getResourceAsStream("/index.html"));
        //Creating a BufferedReader object
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while((str = reader.readLine())!= null){
            if(str.contains("!---INSER_HERE -->")) {
                try {
                    buildingListe = dbAbfrage.sqlAusgabe();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                sb.append("<ul>\n");
                for(Buildings building : buildingListe){
                    sb.append("<li>\n");
                    sb.append("<label>\n");
                    sb.append(building.getName());                                                                              //Label Name aus der map
                    sb.append(" <input type=\"number\" id=\"");                                                                 //input Feld, Id basierned auf der Map
                    sb.append(building.getId());
                    sb.append("\" name=\"");
                    sb.append(building.getName());
                    sb.append("\" value=\"0\" size=\"1\" /\n>");
                    sb.append(" Anzahl Galaxiebonus ");
                    sb.append("<input type =\"number\" id=\"Galaxiebonus.");
                    sb.append(building.getId());
                    sb.append("\" value=\"0\" size=\"1\" />\n");
                    sb.append("</label>\n");

                    sb.append("</li>\n");
                }
                sb.append("</ul>\n");
            } else {
                sb.append(str+"\n");
            }

        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        pw.println(sb);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        final List<BuildingDto> buildings = new ObjectMapper().readValue(request.getReader(), new TypeReference<List<BuildingDto>>(){ });

        AuswertungServlet auswertung = new AuswertungServlet();


        String ergebnis = new String();
        try {
            ergebnis = auswertung.auswerten(buildings);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("result enthält = " + ergebnis);



        pw.close();


        response.sendRedirect("/FoE/EventBuildings");


    }
}
