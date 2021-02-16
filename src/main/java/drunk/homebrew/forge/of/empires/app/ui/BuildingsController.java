package drunk.homebrew.forge.of.empires.app.ui;

import drunk.homebrew.forge.of.empires.app.persistence.BuildingEntity;
import drunk.homebrew.forge.of.empires.app.persistence.HibernateUtil;
import drunk.homebrew.forge.of.empires.app.service.EvaluationOfIncome;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Ein Spring REST Controller für /EventBuildings.
 * Er baut die Seite über GET auf und schickt die Auswertung über POST in Json zurück.
 *
 * @author Dhalia
 */
@RestController
public class BuildingsController {

    @GetMapping(path = "/EventBuildings")
    public String buildWebsite() throws Exception {
        InputStreamReader isReader = new InputStreamReader(getClass().getResourceAsStream("/building.html"));
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();
        String str;
//        BuildingEntity test = new BuildingEntity("Roflcopter", 10, 0, 0, 5, 2000, 500, 1337);

        while ((str = reader.readLine()) != null) {
            if (str.contains("<!-- INSERT_HERE -->")) {
                Transaction transaction = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//                    transaction = session.beginTransaction();
//                    session.save(test);
//                    transaction.commit();
                    List<BuildingEntity> eventBuildings = session.createQuery("from BuildingEntity", BuildingEntity.class).list();
                    sb.append("<ul>\n");
                    for (BuildingEntity building : eventBuildings) {
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

                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                }
            } else {
                sb.append(str);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @PostMapping(path = "/EventBuildings", consumes = "application/json", produces = "application/json")
    public CalculationDto calculateLoot(@RequestBody List<BuildingDto> request) {
        EvaluationOfIncome calculation = new EvaluationOfIncome();
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<BuildingEntity> eventBuildings = session.createQuery("from BuildingEntity", BuildingEntity.class).list();
        return calculation.calculate(request, eventBuildings);
    }

}