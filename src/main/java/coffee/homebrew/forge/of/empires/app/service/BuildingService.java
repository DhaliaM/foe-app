package drunk.homebrew.forge.of.empires.app.service;

import drunk.homebrew.forge.of.empires.app.persistence.BuildingEntity;
import drunk.homebrew.forge.of.empires.app.persistence.BuildingJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Service um Daten in BuildingJpaRepository/der Datenbank zu ändern
 * @author Dhalia
 */
@Service
public class BuildingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildingService.class);
    private BuildingJpaRepository buildingJpaRepository;

    public BuildingService(BuildingJpaRepository buildingJpaRepository)
    {
        this.buildingJpaRepository = buildingJpaRepository;
    }

    //TODO: Gefährlich einfach ein cast durchzuführen ohne zu prüfen, ob es wirklich eine Liste ist
    //TODO: javadoc fehlt
    public List<BuildingEntity> findAll(){
        return (List<BuildingEntity>) buildingJpaRepository.findAll();
    }

    //TODO: javadoc fehlt
    public void delete(BuildingEntity buildingEntity)
    {
        buildingJpaRepository.delete(buildingEntity);
    }

    //TODO: javadoc fehlt
    public void save(BuildingEntity buildingEntity)
    {
        if (buildingEntity == null)
        {
            LOGGER.error("kein Gebäude");
            return;
        }
        buildingJpaRepository.save(buildingEntity);
    }
}
