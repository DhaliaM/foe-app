package drunk.homebrew.forge.of.empires.app;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
        sb.append("<input type=\"submit\" value=\"Submit\">");
        sb.append("</form>");
        sb.append("<script>");
        sb.append("var dataForm = document.getElementById(\"Buildings\")");
        sb.append(("var dataJson = JSON.stringify(dataFrom)"));

        sb.append("</script>");
        sb.append("</body>");
        sb.append("</html>");

        pw.println(sb);
        pw.close();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


    }
}
