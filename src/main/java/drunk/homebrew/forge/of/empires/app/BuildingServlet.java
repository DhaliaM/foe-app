package drunk.homebrew.forge.of.empires.app;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class BuildingServlet extends HttpServlet {
    private String foeAusgabe = new String("Ausgabe");
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        Yaml yaml = new Yaml(new Constructor(LoadProperties.class));
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("application.yaml");
        LoadProperties yamlData = yaml.load(stream);

        Map<String, Buildings> eventBuildings = yamlData.getBuildings();


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
        sb.append("<form action=\"EventBuildings\" name=\"buildings\" id=\"buildings\"");
        sb.append("<ul>");
        for (String key : eventBuildings.keySet()){                                                     //daten aus der map, atm yaml
            sb.append("<li>");
            sb.append("<label>");
            sb.append(eventBuildings.get(key).getName());                                               //Label Name aus der map
            sb.append(" <input type=\"number\" id=\"");                                                 //input Feld, Id basierned auf der Map
            sb.append(key);
            sb.append("\" name=\"");
            sb.append(eventBuildings.get(key).getName());                                               //input Feld Name aus der map, vmtl nutzlos
            sb.append("\" value=\"0\" size=\"1\">");
            sb.append(" Anzahl Galaxiebonus ");
            sb.append("<input type =\"number\" id=\"");
            sb.append(key);
            sb.append(".GalaxieBonus\" ");
            sb.append("value=\"0\" size=\"1\">");
            sb.append("</label>");

            sb.append("</li>");
        }
        sb.append("<input type=\"Button\" id=\"submitButton\" onclick=\"submitJson()\" value=\"Submit\">");
        sb.append("</form>");
        sb.append("</div>");

        sb.append("<div class=\"output\">");

        sb.append(foeAusgabe);


        sb.append("</div>");



        sb.append("<script>");
        sb.append("const form = document.querySelector('form');");
        sb.append("form.addEventListener('submit', event => {event.preventDefault()});");

        //Funktion um die Id aus dem Button ohne Buchstaben zu erhalten, war ursprünglich für etwas anderes
//        sb.append("function splitButtonString(String string){");
//        sb.append("string = string.split(\".\",1)");
//        sb.append("return string;");
//        sb.append("}");

        //Funktion um die Json zu füllen
        sb.append("function submitJson(){");
        sb.append("var dataForm = document.getElementById(\"buildings\");");
        sb.append("var dataInArray = new Map();");
        sb.append("for(let element of dataForm.elements){");                    //ist 1=0  und nachdem alle Gebäude durch sind 1.GalaxieBonus=1
        sb.append("dataInArray[element.id] = element.value;");                  //soll 1:
        sb.append("}");                                                         //      Anzahl=2
                                                                                //      GBonus=1
        sb.append("delete dataInArray[\"submitButton\"];"); //entfernt den Submit Button aus der Map dataInArray
        sb.append("console.log(Object.values(dataInArray));");
        sb.append("var dataJson = JSON.stringify(dataInArray);");
        sb.append("var xhr = new XMLHttpRequest();");
        sb.append("xhr.onreadystatechange = function() {");
        sb.append("if (this.readyState == 4 && this.status == 200) {");
        sb.append("console.log(this.responseText);"); //enthält response von doPost, <div>output mit response füllen somehow
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

        Map<String,Object> result = new ObjectMapper().readValue(request.getInputStream(), Map.class);


        foeAusgabe = "test";

        // to send out the json data
//
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println(result);
        pw.close();

//          doGet(request,response);
        response.sendRedirect("/FoE/EventBuildings");
    }
}
