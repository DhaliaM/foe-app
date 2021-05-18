package coffee.homebrew.forge.of.empires.app.ui;

import coffee.homebrew.forge.of.empires.app.service.BuildingService;
import coffee.homebrew.forge.of.empires.app.service.EvaluationOfIncome;
import coffee.homebrew.forge.of.empires.app.persistence.BuildingEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ein Spring REST Controller für /EventBuildings.
 * Er baut die Seite über GET auf und schickt die Auswertung über POST in Json zurück.
 *
 * @author Dhalia
 */
@Controller
public class BuildingsController {
    final BuildingService buildingService;

    public BuildingsController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/building", method = RequestMethod.GET)
    public String building(Model model) {

        List<BuildingEntity> eventBuildings;
        eventBuildings = buildingService.findAll();
        model.addAttribute("eventBuildings", eventBuildings);
        return "building";
    }

    @RequestMapping(value = "/updateBuildings", method = RequestMethod.GET)
    public String updateBuilding(Model model) {

        List<BuildingEntity> eventBuildings;
        BuildingEntity addBuilding = new BuildingEntity();
        BuildingEntity editBuilding = new BuildingEntity();
        eventBuildings = buildingService.findAll();

        model.addAttribute("eventBuildings", eventBuildings);
        model.addAttribute("addBuilding", addBuilding);
        model.addAttribute("editBuilding", editBuilding);
        return "updateBuildings";
    }

    @RequestMapping(value = "/updateBuildings", method = RequestMethod.POST)
    public String addBuilding(@ModelAttribute BuildingEntity addBuilding, Model model) {

        model.addAttribute("addBuilding", addBuilding);
        if (addBuilding.getName() != "") {
            buildingService.save(addBuilding);
        }

        return "redirect:/updateBuildings";
    }

    @RequestMapping(value = "/updateBuildings", method = RequestMethod.DELETE)
    public void deleteBuilding(@RequestBody List<Integer> idsToDelete, Model model) {

        idsToDelete.forEach(id->{
            BuildingEntity building = new BuildingEntity();
            building.setId(id.longValue());
            buildingService.delete(building);
        });

        model.addAttribute("eventBuildings", buildingService.findAll());
        model.addAttribute("addBuilding", new BuildingEntity());
        model.addAttribute("editBuilding", new BuildingEntity());
    }

    @RequestMapping(value = "/building", method = RequestMethod.POST)
    @ResponseBody
    public CalculationDto calculateLoot(@RequestBody List<BuildingDto> request) {
        EvaluationOfIncome calculation = new EvaluationOfIncome();
        List<BuildingEntity> eventBuildings;
        eventBuildings = buildingService.findAll();
        return calculation.calculate(request, eventBuildings);
    }

}