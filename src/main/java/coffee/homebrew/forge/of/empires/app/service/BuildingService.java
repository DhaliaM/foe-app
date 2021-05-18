package coffee.homebrew.forge.of.empires.app.service;

import coffee.homebrew.forge.of.empires.app.persistence.BuildingEntity;
import coffee.homebrew.forge.of.empires.app.persistence.BuildingJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service um Daten in BuildingJpaRepository/der Datenbank zu ändern.
 *
 * @author Dhalia
 */
@Service
public class BuildingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildingService.class);
    private BuildingJpaRepository buildingJpaRepository;

    public BuildingService(BuildingJpaRepository buildingJpaRepository) {
        this.buildingJpaRepository = buildingJpaRepository;
    }
    /**
     * Gibt alle Objekte in der Datenbank zurück.
     *
     * @return Liste mit Gebäudeobjekten
     */
    public List<BuildingEntity> findAll() {
        return (List<BuildingEntity>) buildingJpaRepository.findAll();
    }

    /**
     * Löscht Objekte in der Datenbank.
     */
    public void delete(BuildingEntity buildingEntity) {
        buildingJpaRepository.delete(buildingEntity);
    }

    /**
     * Legt neue Objekte in der Datenbank an.
     */
    public void save(BuildingEntity buildingEntity) {
        if (buildingEntity == null) {
            LOGGER.error("kein Gebäude");
            return;
        }
        buildingJpaRepository.save(buildingEntity);
    }
}
