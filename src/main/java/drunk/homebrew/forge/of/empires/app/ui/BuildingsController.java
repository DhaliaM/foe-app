package drunk.homebrew.forge.of.empires.app.ui;

import drunk.homebrew.forge.of.empires.app.persistence.BuildingEntity;
import drunk.homebrew.forge.of.empires.app.persistence.HibernateUtil;
import drunk.homebrew.forge.of.empires.app.service.EvaluationOfIncome;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Ein Spring REST Controller für /EventBuildings.
 * Er baut die Seite über GET auf und schickt die Auswertung über POST in Json zurück.
 *
 * @author Dhalia
 */
@Controller
public class BuildingsController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/building", method = RequestMethod.GET)
    public String building(Model model) {

        List<BuildingEntity> eventBuildings = new ArrayList<>();

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            eventBuildings = session.createQuery("from BuildingEntity", BuildingEntity.class).list();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        model.addAttribute("eventBuildings", eventBuildings);
        return "building";
    }

    @RequestMapping(value = "/updateBuildings", method = RequestMethod.GET)
    public String updateBuilding(Model model) {

        List<BuildingEntity> eventBuildings = new ArrayList<>();
        BuildingEntity addBuilding = new BuildingEntity();
        Boolean toDeleted = new Boolean(false);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            eventBuildings = session.createQuery("from BuildingEntity", BuildingEntity.class).list();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        model.addAttribute("eventBuildings", eventBuildings);
        model.addAttribute("addBuilding", addBuilding);
        model.addAttribute("toDeleted", toDeleted);
        return "updateBuildings";
    }

    @RequestMapping(value = "/updateBuildings", method = RequestMethod.POST)
    public String addBuilding(@ModelAttribute Boolean toDeleted, BuildingEntity addBuilding, Model model) {

        model.addAttribute("addBuilding", addBuilding);
        model.addAttribute("toDeleted", toDeleted);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(addBuilding);
        session.getTransaction().commit();
        session.close();

//        return "redirect:/updateBuildings";
        return addBuilding.toString();
    }

    @RequestMapping(value = "/building", method = RequestMethod.POST)
    @ResponseBody
    public CalculationDto calculateLoot(@RequestBody List<BuildingDto> request) {
        EvaluationOfIncome calculation = new EvaluationOfIncome();
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<BuildingEntity> eventBuildings = session.createQuery("from BuildingEntity", BuildingEntity.class).list();
        session.close();
        return calculation.calculate(request, eventBuildings);
    }

}