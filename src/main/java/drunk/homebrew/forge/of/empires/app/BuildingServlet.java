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
        for (String key : eventBuildings.keySet()){
            sb.append("<li>");
            sb.append("<label>");
            sb.append(eventBuildings.get(key).getName());
            sb.append(" <input id=\"");
            sb.append(key);
            sb.append("\" value=\"0\" size=\"1\">");
            sb.append("</label>");
            sb.append("</li>");
        }
        sb.append("<input type=\"Button\" id=\"submitButton\" onclick=\"submitJson()\" value=\"Submit\">");
        sb.append("</form>");
        sb.append("<script>");
        sb.append("const form = document.querySelector('form');");
        sb.append("form.addEventListener('submit', event => {event.preventDefault()});");
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

//        Map<String,Object> result = new ObjectMapper().readValue(request, Map.class);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"de\">");
        sb.append("<head>");
        sb.append("<title>Titel der Seite | Name der Website</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<p>");
        sb.append(request);
        sb.append("</p>");
        sb.append("</body>");
        sb.append("<script>");
        //sb.append("alert ('test')");
        sb.append("</script>");
        sb.append("</html>");

        pw.println(sb);
        pw.close();
    }
}
