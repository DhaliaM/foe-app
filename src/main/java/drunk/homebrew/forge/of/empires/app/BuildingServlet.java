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

//        TransmitBuildings transmitBuildings = new TransmitBuildings();
//        String buildings = transmitBuildings.transmitBuildings();
        //ServletAusgabeTest test = new ServletAusgabeTest();

        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"de\">");
        sb.append("<head>");
        sb.append("<meta charset=\"utf-8\">");
        sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        sb.append("<title>Titel der Seite | Name der Website</title>");
        sb.append("</head>");
        sb.append("<body>");
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
            sb.append("<button id=\"");
            sb.append(key);
            sb.append(".GalaxieBonus\" ");
            sb.append("onclick=\"generateGalxieBonusCheckBox(this)\">");
            sb.append("Galxiebonus");
            sb.append("</button>");
            sb.append("</label>");
            sb.append("</li>");
        }
        sb.append("<input type=\"Button\" id=\"submitButton\" onclick=\"submitJson()\" value=\"Submit\">");
        sb.append("</form>");

        sb.append("<script>");
        sb.append("const form = document.querySelector('form');");
        sb.append("form.addEventListener('submit', event => {event.preventDefault()});");
        //Funktion um die Id aus dem Button ohne Buchstaben zu erhalten
        sb.append("function splitButtonString(String string){");
        sb.append("string = string.split(\".\",1)");
        sb.append("return string;");
        sb.append("}");
        // Funktion um, basierend auf der Anzahl in input.value, checkboxen zu erstellen für den evtl. GalaxieBonus
        sb.append("function generateGalaxieBonusCheckBox(buttonId){");
        sb.append("var id = splitButtonString(buttonId.id)");
        //sb.append("var inputValue = getElementById(id).value;");
        sb.append("if(getElementById(id).value > 0){");
        sb.append("for(i = 1; i < getElementById(id).value; i++){");

//        sb.append("console.log()");
//        function to create checkboxes numbering the input.id.value, named input.id +.+ "gBonus" +.+ for(i=1; 1 to input.id.value  , appenchild Button?
        sb.append("}");
        sb.append("function submitJson(){");
        sb.append("var dataForm = document.getElementById(\"buildings\");");
        sb.append("var dataInArray = new Map();");
        sb.append("for(let element of dataForm.elements){");
        sb.append("dataInArray[element.id] = element.value;");
        sb.append("}");
        sb.append("delete dataInArray[\"submitButton\"];"); //entfernt den Submit Button aus der Map dataInArray
        //sb.append("console.log(Object.values(dataInArray));");
        sb.append("var dataJson = JSON.stringify(dataInArray);");
        sb.append("var xhr = new XMLHttpRequest();");
        sb.append("xhr.open(\"POST\", \"/FoE/EventBuildings\", true);");
        sb.append("xhr.setRequestHeader('Content-Type', 'application/json');");
        sb.append("xhr.send(dataJson);");
        //sb.append("alert (dataJson);"); //nur zum testen was in dataJson drin steht
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

//        Map<String,Object> result = new ObjectMapper().readValue(request.getInputStream(), Map.class);




        // to send out the json data

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(request);
        out.close();

    }
}
