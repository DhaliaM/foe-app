package drunk.homebrew.forge.of.empires.app.ui;

import com.fasterxml.jackson.core.type.TypeReference;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BuildingServlet extends HttpServlet {


    public BuildingServlet() throws SQLException, NamingException {
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DbAnbindung dbAbfrage = null;
        try {
            dbAbfrage = new DbAnbindung();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }


        List<Buildings> buildingListe = new ArrayList<Buildings>();

        InputStreamReader isReader = new InputStreamReader(getClass().getResourceAsStream("/index.html"));
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str = reader.readLine()) != null) {
            if (str.contains("<!-- INSERT_HERE -->")) {
                try {
                    buildingListe = dbAbfrage.sqlAusgabe();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                sb.append("<ul>\n");
                for (Buildings building : buildingListe) {
                    sb.append("<li>\n");
                    sb.append("<label>\n");
                    sb.append(building.getName());
                    sb.append(" <input type=\"number\" id=\"");
                    sb.append(building.getId());
                    sb.append("\" name=\"");
                    sb.append(building.getName());
                    sb.append("\" value=\"0\" size=\"1\" /\n>");
                    sb.append(" Anzahl Galaxiebonus ");
                    sb.append("<input type =\"number\" id=\"Galaxiebonus.");
                    sb.append(building.getId());
                    sb.append("\" value=\"0\" size=\"1\"/>\n");
                    sb.append("</label>\n");
                    sb.append("</li>\n");
                }
                sb.append("</ul>\n");
            } else {
                sb.append(str + "\n");
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

        final List<BuildingDto> buildings = new ObjectMapper().readValue(request.getReader(), new TypeReference<List<BuildingDto>>() {
        });

        AuswertungServlet auswertung = new AuswertungServlet();


        String ergebnis = new String();
        try {
            ergebnis = auswertung.auswerten(buildings);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("result enthält = " + ergebnis);


        pw.close();


//        response.sendRedirect("/FoE/EventBuildings");


    }
}
